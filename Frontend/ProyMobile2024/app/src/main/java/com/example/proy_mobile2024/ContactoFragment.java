package com.example.proy_mobile2024;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.repository.PerfilRepository;

import java.util.List;


public class ContactoFragment extends Fragment {

    private EditText etName, etTel, etEmail, etMsj;
    private Button btnEnviar;

    public ContactoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        inicializarVistas(view);
        aplicarValidaciones();

        // Leer SharedPreferences
        SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String nombre = preferences.getString("username", null);
        String email = preferences.getString("email", null);

        if(nombre != null && email != null){
            etName.setText(nombre);
            etEmail.setText(email);
            Log.d("ContactoDebug", "Nombre: " + nombre + ", Email: " + email);
        } else {
            Log.d("ContactoDebug", "No se encontró el usuario en SharedPreferences");
        }

        btnEnviar.setOnClickListener(v -> enviarMensaje());

        return view;
    }



    private void inicializarVistas(View view) {
        etName = view.findViewById(R.id.et_name);
        etTel = view.findViewById(R.id.et_tel);
        etEmail = view.findViewById(R.id.et_email);
        etMsj = view.findViewById(R.id.et_msj);
        btnEnviar = view.findViewById(R.id.btn_enviar);
    }

    private void aplicarValidaciones() {
        etName.addTextChangedListener(createSanitizingTextWatcher(etName, "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));
        etMsj.addTextChangedListener(createSanitizingTextWatcher(etMsj, "[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ,.?!]+"));
    }

    private void enviarMensaje() {
        String nombre = etName.getText().toString().trim();
        String telefono = etTel.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mensaje = etMsj.getText().toString();

        String validacion = validarCampos(nombre, telefono, email, mensaje);

        if (validacion == null) {
            // Aquí podrías hacer una llamada a la API para enviar el mensaje si deseas.
            Toast.makeText(getContext(), "Mensaje enviado correctamente", Toast.LENGTH_LONG).show();
            etMsj.setText("");
        } else {
            Toast.makeText(getContext(), validacion, Toast.LENGTH_LONG).show();
        }
    }

    private String validarCampos(String nombre, String telefono, String email, String mensaje) {
        if (nombre.isEmpty()) return "El campo de nombre está vacío.";
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) return "El nombre solo puede contener letras y espacios.";
        if (nombre.length() > 30) return "El nombre no puede exceder los 30 caracteres.";

        if (telefono.isEmpty()) return "El campo de teléfono está vacío.";
        if (telefono.length() > 15) return "El número de teléfono no puede exceder los 15 caracteres.";
        if (!android.util.Patterns.PHONE.matcher(telefono).matches()) return "El formato del teléfono es incorrecto.";

        if (email.isEmpty()) return "El campo de email está vacío.";
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "El formato del email es incorrecto.";

        if (mensaje.trim().isEmpty()) return "El campo de mensaje no puede estar vacío o contener solo espacios.";
        if (mensaje.length() > 250) return "El mensaje no puede exceder los 250 caracteres.";

        return null;
    }

    private TextWatcher createSanitizingTextWatcher(EditText editText, String allowedPattern) {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                String sanitized = s.toString().replaceAll("[^" + allowedPattern + "]", "");
                if (!sanitized.equals(s.toString())) {
                    editText.setText(sanitized);
                    editText.setSelection(sanitized.length());
                }
            }
        };
    }
}