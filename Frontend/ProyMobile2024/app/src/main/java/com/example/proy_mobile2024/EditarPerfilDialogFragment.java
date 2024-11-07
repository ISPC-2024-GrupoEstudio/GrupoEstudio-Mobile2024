package com.example.proy_mobile2024;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
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
        EditText apellidoEditText = view.findViewById(R.id.editar_apellido);
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
                nombreEditText.setText(TextUtils.htmlEncode(usuarioPerfil.getNombre() != null ? usuarioPerfil.getNombre() : ""));
                apellidoEditText.setText(TextUtils.htmlEncode(usuarioPerfil.getApellido() != null ? usuarioPerfil.getApellido() : ""));
                emailEditText.setText(TextUtils.htmlEncode(usuarioPerfil.getEmail() != null ? usuarioPerfil.getEmail() : ""));
                telefonoEditText.setText(usuarioPerfil.getNro_telefono() != 0 ? String.valueOf(usuarioPerfil.getNro_telefono()) : "");
                direccionEditText.setText(TextUtils.htmlEncode(usuarioPerfil.getDireccion() != null ? usuarioPerfil.getDireccion() : ""));
                dniEditText.setText(usuarioPerfil.getDni() != 0 ? String.valueOf(usuarioPerfil.getDni()) : "");
            }
        });

        nombreEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence caracter, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence caracter, int start, int before, int count) {
                if (!isValidNombre(caracter.toString())){
                    nombreEditText.setError("Solo se permiten letras");
                }
            }

            @Override
            public void afterTextChanged(Editable caracter) {
                if (caracter.length() > 50){
                    nombreEditText.setError("Máximo de 50 caracteres");
                }
            }
        });

        apellidoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence caracter, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence caracter, int start, int before, int count) {
                if (!isValidNombre(caracter.toString())){
                    apellidoEditText.setError("Solo se permiten letras");
                }
            }

            @Override
            public void afterTextChanged(Editable caracter) {
                if (caracter.length() > 50){
                    apellidoEditText.setError("Máximo de 50 caracteres");
                }
            }
        });

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence caracter, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence caracter, int start, int before, int count) {
                if (!isValidEmail(caracter.toString())){
                    emailEditText.setError("Correo electrónico inválido");
                }else{
                    emailEditText.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable caracter) {}
        });

        direccionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence caracter, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence caracter, int start, int before, int count) {
                String direccion = caracter.toString();
                if (!isValidDireccion(direccion)){
                    if (!contieneNumero(direccion)){
                        direccionEditText.setError("La dirección debe tener al menos un número");
                    }else {
                        direccionEditText.setError("La dirección no puede contener símbolos");
                    }
                }else{
                    direccionEditText.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable caracter) {}
        });


        guardarCambiosBtn.setOnClickListener(v -> {
            String telefono = telefonoEditText.getText().toString();
            String dni = dniEditText.getText().toString();

            if (!validarCampos(nombreEditText.getText().toString(), apellidoEditText.getText().toString(),emailEditText.getText().toString(),telefono,direccionEditText.getText().toString(), dni)){
                return;
            }

            try {
                String nombreUsuarioActual = obtenerUsernameUsuario();

                long telefonoLong = Long.parseLong(telefono);
                long dniLong = Long.parseLong(dni);

                UsuarioPerfil perfilActualizado = new UsuarioPerfil(
                        TextUtils.htmlEncode(nombreEditText.getText().toString()),
                        TextUtils.htmlEncode(apellidoEditText.getText().toString()),
                        nombreUsuarioActual,
                        TextUtils.htmlEncode(emailEditText.getText().toString()),
                        telefonoLong,
                        TextUtils.htmlEncode(direccionEditText.getText().toString()),
                        dniLong,
                        perfilImageUrl

                );


                if (nombreUsuarioActual.isEmpty()){
                    Toast.makeText(requireContext(), "Nombre de usuario obligatorio", Toast.LENGTH_LONG).show();
                    return;
                }


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
                dismiss();

            } catch (NumberFormatException e){
                mostrarError("Por favor, ingrese valores válidos para teléfono y DNI");
            }
        });

        init(view);

    }

    private boolean validarCampos(String nombre, String apellido, String email,String telefono,String direccion, String dni){
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || dni.isEmpty()){
            mostrarError("Los campos no pueden estar vacíos");
            return false;
        }

        if (nombre.length() < 2){
            mostrarError("El nombre debe tener un mínimo de 2 caracteres");
            return false;
        }

        if (nombre.length() > 50){
            mostrarError("El nombre puede tener un máximo de 50 caracteres");
            return false;
        }

        if (apellido.length() < 2){
            mostrarError("El apellido debe tener un mínimo de 2 caracteres");
            return false;
        }

        if (apellido.length() > 50){
            mostrarError("El apellido puede tener un máximo de 50 caracteres");
            return false;
        }

        if (telefono.length() != 10 ){
            mostrarError("El teléfono debe tener 10 dígitos");
            return false;
        }

        if (!isValidDireccion(direccion)){
            mostrarError("La direccion no puede contener simbolos ");
            return false;
        }

        if (dni.length() != 8){
            mostrarError("El DNI debe tener 8 digitos");
            return false;
        }

        if (!isValidNombre(nombre)){
            mostrarError("Solo se permiten letras en el nombre");
            return false;
        }

        if (!isValidNombre(apellido)){
            mostrarError("Solo se permiten letras en el apellido");
            return false;
        }

        if (email.length() < 5){
            mostrarError("El email debe tener un minimo de 5 caracteres");
            return false;
        }

        if (!isValidEmail(email)){
            mostrarError("Correo electrónico inválido");
            return false;
        }

        if (!telefono.matches("\\d+")){
            mostrarError("Por favor ingrese solo números en el teléfono");
            return false;
        }

        if (!dni.matches("\\d+")){
            mostrarError("Por favor ingrese solo números en el DNI");
            return false;
        }

        return true;
    }

    private boolean isValidNombre(String text){
        return text.matches("^[a-zA-Z\\s]+$");
    }

    private boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidDireccion(String direccion){
        return direccion.length() >= 5 && direccion.length() <= 255 && direccion.matches("^[a-zA-Z0-9\\s]+$") && contieneNumero(direccion);
    }

    private boolean contieneNumero(String str){
        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private void mostrarError(String mensaje){
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show();
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
