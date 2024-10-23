package com.example.proy_mobile2024;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.viewsmodels.PerfilViewModel;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilActivity extends AppCompatActivity {


    private ImageView imageview;

    private PerfilViewModel perfilViewModel;
    private TextView textViewPerfil;
    private TextView usernameTextView;
    private TextView nombreTextView;
    private TextView emailTextView;
    private TextView telefonoTextView;
    private TextView direccionTextView;
    private TextView dniTextView;

    //Manejo de la imagen de perfil
    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null){
            Uri imageUri = result.getData().getData();
            if (imageUri != null){
                Glide.with(this).load(imageUri).transform(new CircleCrop()).into(imageview);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageview.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("PerfilActivity", "Error al cargar la imagen", e);
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


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        textViewPerfil = findViewById(R.id.perfil_header_username);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        perfilViewModel = new PerfilViewModel(getApplicationContext());

        usernameTextView = findViewById(R.id.perfil_user_txt);
        nombreTextView = findViewById(R.id.perfil_name_txt);
        emailTextView = findViewById(R.id.perfil_email_txt);
        telefonoTextView = findViewById(R.id.perfil_telefono_txt);
        direccionTextView = findViewById(R.id.perfil_direccion_txt);
        dniTextView = findViewById(R.id.perfil_dni_txt);

        obtenerDatosUsuario();



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
        Log.e("entrando", "perfil en la activity");
        perfilViewModel.fetchPerfil();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageview = findViewById(R.id.perfil_circulo_img);
        findViewById(R.id.floatingActionButton).setOnClickListener(v -> checkMediaPermission());

        init();
    }

    private void obtenerDatosUsuario(){
        RetrofitClient.getInstance(this).getApiService().getPerfil().enqueue(new Callback<List<UsuarioPerfil>>() {
            @Override
            public void onResponse(Call<List<UsuarioPerfil>> call, Response<List<UsuarioPerfil>> response){
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()){
                    List<UsuarioPerfil> usuarios = response.body();
                    UsuarioPerfil usuarioPerfil = usuarios.get(7);
                    Log.e("Telefono", "NRO --> " + usuarioPerfil.getNro_telefono());

                    textViewPerfil.setText(usuarioPerfil.getUser_name());
                    usernameTextView.setText(usuarioPerfil.getUser_name());
                    nombreTextView.setText(usuarioPerfil.getNombreCompleto());
                    emailTextView.setText(usuarioPerfil.getEmail());
                    telefonoTextView.setText(Integer.toString(usuarioPerfil.getNro_telefono()));
                    direccionTextView.setText(usuarioPerfil.getDireccion());
                    dniTextView.setText(Integer.toString(usuarioPerfil.getDni()));
                } else {
                    Toast.makeText(PerfilActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<UsuarioPerfil>> call, Throwable t){
                Toast.makeText(PerfilActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
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

