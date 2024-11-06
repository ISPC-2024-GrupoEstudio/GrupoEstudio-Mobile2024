package com.example.proy_mobile2024;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.viewsmodels.PerfilViewModel;


public class EditarPerfilDialogFragment extends DialogFragment {
    private PerfilViewModel perfilViewModel;
    private OnPerfilEditListener listener;
    private String perfilImageUrl;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            perfilImageUrl = getArguments().getString("perfilImageUrl");
            Log.d("EditPerfil", perfilImageUrl);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_editar_perfil, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        perfilViewModel = new ViewModelProvider(requireActivity()).get(PerfilViewModel.class);


        EditText nombreEditText = view.findViewById(R.id.editar_nombre);
        EditText apelidoEditText = view.findViewById(R.id.editar_apellido);
        EditText emailEditText = view.findViewById(R.id.editar_email);
        EditText telefonoEditText = view.findViewById(R.id.editar_telefono);
        EditText direccionEditText = view.findViewById(R.id.editar_direccion);
        EditText dniEditText = view.findViewById(R.id.editar_dni);


        Button guardarCambiosBtn = view.findViewById(R.id.btn_guardar_cambios);

        perfilViewModel.getMensajeError().observe(getViewLifecycleOwner(), error -> {
            if (error != null){
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });


        perfilViewModel.getUsuarioPerfil().observe(getViewLifecycleOwner(), usuarioPerfil -> {
            if (usuarioPerfil != null){
                nombreEditText.setText(usuarioPerfil.getNombre() != null ? usuarioPerfil.getNombre() : "");
                apelidoEditText.setText(usuarioPerfil.getApellido() != null ? usuarioPerfil.getApellido() : "");
                emailEditText.setText(usuarioPerfil.getEmail() != null ? usuarioPerfil.getEmail() : "");
                telefonoEditText.setText(usuarioPerfil.getNro_telefono() != 0 ? String.valueOf(usuarioPerfil.getNro_telefono()) : "");
                direccionEditText.setText(usuarioPerfil.getDireccion() != null ? usuarioPerfil.getDireccion() : "");
                dniEditText.setText(usuarioPerfil.getDni() != 0 ? String.valueOf(usuarioPerfil.getDni()) : "");
            }
        });


        guardarCambiosBtn.setOnClickListener(v -> {
            String telefono = telefonoEditText.getText().toString();
            String dni = dniEditText.getText().toString();

            if (!validarCampos(telefono, dni)){
                return;
            }

            try {
                String nombreUsuarioActual = obtenerUsernameUsuario();

                long telefonoLong = Long.parseLong(telefono);
                long dniLong = Long.parseLong(dni);

                UsuarioPerfil perfilActualizado = new UsuarioPerfil(
                        nombreEditText.getText().toString(),
                        apelidoEditText.getText().toString(),
                        nombreUsuarioActual,
                        emailEditText.getText().toString(),
                        telefonoLong,
                        direccionEditText.getText().toString(),
                        dniLong,
                        perfilImageUrl

                );


                if (nombreUsuarioActual.isEmpty()){
                    Toast.makeText(requireContext(), "Nombre de usuario obligatorio", Toast.LENGTH_SHORT).show();
                    return;
                }

                perfilViewModel.getActualizacionExitosa().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<Boolean>(){
                    @Override
                    public void onChanged(Boolean exitoso){
                        if (exitoso != null && exitoso){
                            Toast.makeText(requireContext(), "Perfil actualizado con exito", Toast.LENGTH_SHORT).show();
                            perfilViewModel.getActualizacionExitosa().removeObserver(this);
                            dismiss();
                        }
                    }
                });

                perfilViewModel.actualizarPerfil(nombreUsuarioActual, perfilActualizado);

                if (listener != null){
                    listener.onPerfilEdit(
                            perfilActualizado.getNombre(),
                            perfilActualizado.getApellido(),
                            perfilActualizado.getEmail(),
                            perfilActualizado.getNro_telefono(),
                            perfilActualizado.getDireccion(),
                            perfilActualizado.getDni(),
                            perfilActualizado.getFotoPerfil()
                    );
                }
            } catch (NumberFormatException e){
                mostrarError("Por favor, ingrese valores válidos para teléfono y DNI");
            }
        });

        init(view);

    }

    private boolean validarCampos(String telefono, String dni){
        if (telefono.isEmpty() || dni.isEmpty()){
            mostrarError("Los campos no pueden estar vacíos");
            return false;
        }

        if (telefono.length() > 10){
            mostrarError("El teléfono debe tener máximo 10 dígitos");
            return false;
        }

        if (dni.length() > 8){
            mostrarError("El DNI debe tener máximo 8 digitos");
            return false;
        }

        if (!telefono.matches("\\d+") || !dni.matches("\\d+")){
            mostrarError("Por favor ingrese solo números");
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje){
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    private String obtenerUsernameUsuario(){
        SharedPreferences preferences = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getString("username", null);
    }


    @Override
    public int getTheme(){
        return R.style.CustomDialogTheme;
    }


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof OnPerfilEditListener){
            listener = (OnPerfilEditListener) context;
        }else {
            throw new RuntimeException(context.toString() + " debe implementar OnPerfilEditListener");
        }
    }


    public interface OnPerfilEditListener{
        void onPerfilEdit(String nombre, String apellido, String email,long telefono, String direccion, long dni, String fotoPerfil );
    }

    public void init(View view) {
        ImageView btnVolverPerfilEditar = view.findViewById(R.id.btnVolverPerfilEditar);

        btnVolverPerfilEditar.setOnClickListener(v -> {
            dismiss();
        });
    }

}
