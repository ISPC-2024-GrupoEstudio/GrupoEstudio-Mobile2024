package com.example.proy_mobile2024;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.viewsmodels.PerfilViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity implements EditarPerfilDialogFragment.OnPerfilEditListener {


    private ImageView imageview;
    private PerfilViewModel perfilViewModel;
    private TextView textViewHeaderUsername;
    private TextView nombreTextView;
    private TextView apellidoTextView;
    private TextView emailTextView;
    private TextView telefonoTextView;
    private TextView direccionTextView;
    private TextView dniTextView;
    private UsuarioPerfil usuarioPerfilActual;
    private String perfilImageUrl;
    private Cloudinary cloudinary;
    private ExecutorService executorService;
    private ImageView perfil_circulo_img;


    //Manejo de la imagen de perfil
    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null){
            Uri imageUri = result.getData().getData();
            if (imageUri != null){
                Glide.with(this).load(imageUri).transform(new CircleCrop()).into(imageview);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    subirImagenAlServidor(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });


    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted){
            openGallery();
        }else{
            Toast.makeText(this, "Permiso de galeria denegado", Toast.LENGTH_SHORT).show();
        }
    });



    private String subirImagenAlServidor(Bitmap bitmap) {
        executorService.submit(() -> {
            try {
                File tempFile = new File(getCacheDir(), "temp_img.jpg");
                FileOutputStream out = new FileOutputStream(tempFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();

                Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
                perfilImageUrl = (String) uploadResult.get("secure_url");
                Log.d("PerfilActivity", perfilImageUrl);

                runOnUiThread(() -> actualizarFotoPerfil(perfilImageUrl));
            } catch (Exception e) {
                Log.e("PerfilActivity", "Error al subir la imagen" + e.getMessage(), e);
                runOnUiThread(() -> Toast.makeText(this, "Error al subir la imagen: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
        return perfilImageUrl;
    }

    public void actualizarFotoPerfil(String nuevaFotoPerfil){
        String usernameOriginal = obtenerUsernameUsuario();

        if (usuarioPerfilActual != null){
            usuarioPerfilActual.setFotoPerfil(nuevaFotoPerfil);
            perfilViewModel.actualizarPerfil(usernameOriginal, usuarioPerfilActual);
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        String username = obtenerUsernameUsuario();
        Button btnEditarPerfil = findViewById(R.id.btn_editar_perfil);

        imageview = findViewById(R.id.perfil_name_img);
        textViewHeaderUsername = findViewById(R.id.perfil_header_username);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        executorService = Executors.newSingleThreadExecutor();

        nombreTextView = findViewById(R.id.perfil_name_txt);
        apellidoTextView = findViewById(R.id.perfil_apellido_txt);
        emailTextView = findViewById(R.id.perfil_email_txt);
        telefonoTextView = findViewById(R.id.perfil_telefono_txt);
        direccionTextView = findViewById(R.id.perfil_direccion_txt);
        dniTextView = findViewById(R.id.perfil_dni_txt);

        btnEditarPerfil.setOnClickListener(v -> {
            EditarPerfilDialogFragment dialogFragment = new EditarPerfilDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "EditarPerfilDialogo");
        });


        perfilViewModel.getCargando().observe(this, cargando -> {
            if (cargando != null && cargando) {
                // Mostrar el ProgressBar
                progressBar.setVisibility(View.VISIBLE);
            } else {
                // Ocultar el ProgressBar
                progressBar.setVisibility(View.GONE);
            }
        });


        perfilViewModel.getMensajeError().observe(this, mensajeError -> {
            if (mensajeError != null) {
                Toast.makeText(this, mensajeError, Toast.LENGTH_LONG).show();
            };
        });

        perfilViewModel.getActualizacionExitosa().observe(this, exitoso -> {
            if (exitoso != null && exitoso) {
                Toast.makeText(this, "Foto de perfil actualizada exitosamente", Toast.LENGTH_SHORT).show();
                actualizarUI(usuarioPerfilActual);
            }
        });

        perfilViewModel.getUsuarioPerfil().observe(this, usuarioPerfil -> {
            if (usuarioPerfil != null){
                obtenerDatosUsuario();
            }
        });

        perfilViewModel.fetchPerfil(username);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageview = findViewById(R.id.perfil_circulo_img);
        findViewById(R.id.floatingActionButton).setOnClickListener(v -> checkMediaPermission());

        initCloudinary();
        init();
    }

    private void initCloudinary(){
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dgql9nx7t",
                "api_key", "734796174896948",
                "api_secret", "DgZAaxaFDRyLs5UQSLxf3mtUUwY"
        ));
    }

    @Override
    public void onPerfilEdit(String nombre, String apellido, String email,long telefono, String direccion, long dni, String fotoPerfil){

        String usernameOriginal = obtenerUsernameUsuario();

        if (usuarioPerfilActual != null){
            usuarioPerfilActual.setNombre(nombre);
            usuarioPerfilActual.setApellido(apellido);
            usuarioPerfilActual.setEmail(email);
            usuarioPerfilActual.setNro_telefono(telefono);
            usuarioPerfilActual.setDireccion(direccion);
            usuarioPerfilActual.setDni(dni);
            usuarioPerfilActual.setFotoPerfil(fotoPerfil);

            perfilViewModel.actualizarPerfil(usernameOriginal, usuarioPerfilActual);

        }
    }

    private void actualizarUI(UsuarioPerfil usuario){
        if (usuario != null){
            nombreTextView.setText(usuario.getNombre() == null || usuario.getNombre().isEmpty() ? "Nombre no proporcionado" : usuario.getNombre());
            apellidoTextView.setText(usuario.getApellido() == null || usuario.getApellido().isEmpty() ? "Apellido no proporcionado" : usuario.getApellido());
            emailTextView.setText(usuario.getEmail() == null || usuario.getEmail().isEmpty() ? "Email no proporcionado" : usuario.getEmail());
            telefonoTextView.setText(usuario.getNro_telefono() == 0 ? "Teléfono no proporcionado" : String.valueOf(usuario.getNro_telefono()));
            direccionTextView.setText(usuario.getDireccion() == null || usuario.getDireccion().isEmpty() ? "Dirección no proporcionada" : usuario.getDireccion());
            dniTextView.setText(usuario.getDni() == 0 ? "DNI no proporcionado" : String.valueOf(usuario.getDni()));

            perfil_circulo_img = findViewById(R.id.perfil_circulo_img);
            Glide.with(this)
                    .load(usuario.getFotoPerfil())
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.foto_icon)
                    .error(R.drawable.foto_icon)
                    .into(perfil_circulo_img);
        }
    }

    private String obtenerUsernameUsuario(){
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getString("username", null);
    }


    private void obtenerDatosUsuario(){
        RetrofitClient.getInstance(this).getApiService().getPerfil().enqueue(new Callback<List<UsuarioPerfil>>() {
            @Override
            public void onResponse(Call<List<UsuarioPerfil>> call, Response<List<UsuarioPerfil>> response){
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()){
                    List<UsuarioPerfil> usuarios = response.body();
                    String usernameUsuario = obtenerUsernameUsuario();
                    UsuarioPerfil usuarioPerfil = null;
                    int usuarioIndex = -1;

                    for (int i = 0; i < usuarios.size(); i++){
                        UsuarioPerfil usuario = usuarios.get(i);
                        if (usuario.getUser_name().equals(usernameUsuario)){
                            usuarioPerfil = usuario;
                            usuarioIndex = i;
                            break;
                        }
                    }

                    if (usuarioPerfil != null){
                        usuarioPerfilActual = usuarioPerfil;
                        textViewHeaderUsername.setText(usuarioPerfil.getUser_name());
                        actualizarUI(usuarioPerfil);
                    }else{
                        Toast.makeText(PerfilActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PerfilActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<UsuarioPerfil>> call, Throwable t){
                Toast.makeText(PerfilActivity.this, "Error perfil: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    //Permisos acceso a galeria de fotos
    private void checkMediaPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED){
                openGallery();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                openGallery();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }


    //Acceder a la galeria
    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(intent);
    }


    public void init(){
        ImageView btnVolverPerfil = findViewById(R.id.btnVolverPerfil);

        btnVolverPerfil.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
