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
        //return inflater.inflate(R.layout.fragment_contacto, container, false);
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

    // Método para validar los campos
    private String validarCampos(String nombre, String telefono, String email, String mensaje) {
        if (nombre.isEmpty()) {
            return "El campo de nombre está vacío.";
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return "El nombre solo puede contener letras y espacios.";
        }
        if (telefono.isEmpty()) {
            return "El campo de teléfono está vacío.";
        }
        if (!android.util.Patterns.PHONE.matcher(telefono).matches()) {
            return "El formato del teléfono es incorrecto.";
        }
        if (email.isEmpty()) {
            return "El campo de email está vacío.";
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "El formato del email es incorrecto.";
        }
        if (mensaje.isEmpty()) {
            return "El campo de mensaje está vacío.";
        }
        return null;
    }

    // Método para enviar el mensaje
    private void enviarMensaje() {
        String nombre = etName.getText().toString();
        String telefono = etTel.getText().toString();
        String email = etEmail.getText().toString();
        String mensaje = etMsj.getText().toString();

        String validacion = validarCampos(nombre, telefono, email, mensaje);
        if (validacion == null) {
            // Mostrar mensaje de éxito
            Toast.makeText(getContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();

            // Vaciar los campos del formulario
            etName.setText("");
            etTel.setText("");
            etEmail.setText("");
            etMsj.setText("");
        } else {
            // Mostrar mensaje de error específico
            Toast.makeText(getContext(), validacion, Toast.LENGTH_SHORT).show();
        }
    }

}