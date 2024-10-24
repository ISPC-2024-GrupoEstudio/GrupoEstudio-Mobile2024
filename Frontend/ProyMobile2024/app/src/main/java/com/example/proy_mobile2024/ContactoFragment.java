package com.example.proy_mobile2024;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.Toast;


public class ContactoFragment extends Fragment {

    private EditText etName, etTel, etEmail, etMsj;

    public ContactoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        etName = view.findViewById(R.id.et_name);
        etTel = view.findViewById(R.id.et_tel);
        etEmail = view.findViewById(R.id.et_email);
        etMsj = view.findViewById(R.id.et_msj);

        view.findViewById(R.id.btn_enviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });

        return view;
    }

    private String validarCampos(String nombre, String telefono, String email, String mensaje) {
        // Validación del nombre
        if (nombre.isEmpty()) {
            return "El campo de nombre está vacío.";
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return "El nombre solo puede contener letras y espacios.";
        }
        if (nombre.length() > 30) { // Validación de longitud del nombre
            return "El nombre no puede exceder los 30 caracteres.";
        }

        // Validación del teléfono
        if (telefono.isEmpty()) {
            return "El campo de teléfono está vacío.";
        }
        if (telefono.length() > 15) { // Límite de 15 caracteres
            return "El número de teléfono no puede exceder los 15 caracteres.";
        }
        if (!android.util.Patterns.PHONE.matcher(telefono).matches()) {
            return "El formato del teléfono es incorrecto.";
        }

        // Validación del email
        if (email.isEmpty()) {
            return "El campo de email está vacío.";
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "El formato del email es incorrecto.";
        }

        // Validación del mensaje
        if (mensaje.trim().isEmpty()) { // Verificar si el mensaje está vacío o solo tiene espacios
            return "El campo de mensaje no puede estar vacío o contener solo espacios.";
        }
        if (mensaje.length() > 250) {
            return "El mensaje no puede exceder los 250 caracteres.";
        }
        return null;
    }

    private void enviarMensaje() {
        String nombre = etName.getText().toString().trim();
        String telefono = etTel.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mensaje = etMsj.getText().toString();

        String validacion = validarCampos(nombre, telefono, email, mensaje);

        if (validacion == null) {
            Toast.makeText(getContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();

            etName.setText("");
            etTel.setText("");
            etEmail.setText("");
            etMsj.setText("");
        } else {
            Toast.makeText(getContext(), validacion, Toast.LENGTH_SHORT).show();
        }
    }
}