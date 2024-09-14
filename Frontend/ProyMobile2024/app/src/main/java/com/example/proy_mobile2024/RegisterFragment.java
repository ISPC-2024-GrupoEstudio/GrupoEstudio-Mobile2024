package com.example.proy_mobile2024;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    private EditText etNombre, etApellido, etTipoDni, etDni, etUsuario, etEmail, etContrasena, etConfirmarContrasena;
    private Button btnRegistrar;

    // Constantes para validaciones
    private static final int MIN_USER_LENGTH = 5; // Mínimo de 5 caracteres para el usuario
    private static final int MIN_PASSWORD_LENGTH = 8; // Mínimo de 8 caracteres para la contraseña

    public RegisterFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Inicializar los elementos de la interfaz
        etNombre = view.findViewById(R.id.et_name);
        etApellido = view.findViewById(R.id.et_lastname);
        etTipoDni = view.findViewById(R.id.dniType);
        etDni = view.findViewById(R.id.dni);
        etUsuario = view.findViewById(R.id.et_username);
        etEmail = view.findViewById(R.id.et_email);
        etContrasena = view.findViewById(R.id.et_password);
        etConfirmarContrasena = view.findViewById(R.id.et_confirm_password);
        btnRegistrar = view.findViewById(R.id.btn_Registro);

        // Configurar el evento click del botón Registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtener los valores ingresados
                String nombre = etNombre.getText().toString().trim();
                String apellido = etApellido.getText().toString().trim();
                String tipoDni = etTipoDni.getText().toString().trim();
                String dni = etDni.getText().toString().trim();
                String usuario = etUsuario.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();
                String confirmarContrasena = etConfirmarContrasena.getText().toString().trim();

                // Validaciones
                if (nombre.isEmpty() || apellido.isEmpty() || tipoDni.isEmpty() || dni.isEmpty() ||
                        usuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                    Toast.makeText(getActivity(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar DNI numérico
                if (!dni.matches("\\d+")) {
                    Toast.makeText(getActivity(), "El DNI debe contener solo números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar formato de email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getActivity(), "Email no válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar longitud mínima de usuario
                if (usuario.length() < MIN_USER_LENGTH) {
                    Toast.makeText(getActivity(), "El usuario debe tener al menos " + MIN_USER_LENGTH + " caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar longitud mínima de contraseña
                if (contrasena.length() < MIN_PASSWORD_LENGTH) {
                    Toast.makeText(getActivity(), "La contraseña debe tener al menos " + MIN_PASSWORD_LENGTH + " caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que las contraseñas coinciden
                if (!contrasena.equals(confirmarContrasena)) {
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aquí puedes llamar a tu API para verificar si el usuario o email ya existen
                checkUserOrEmailExists(usuario, email);  // Simulación de API para verificar

                // Registro exitoso
                saveUserToDatabase(nombre, apellido, tipoDni, dni, usuario, email, contrasena);

                // Simulación de registro exitoso
                Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                // Navegar al LoginActivity o LoginFragment
                /*getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment())
                        .commit();*/
            }
        });

        return view;
    }

    // Método para verificar si el usuario o email ya existen (Simulación)
    private void checkUserOrEmailExists(String username, String email) {
        // Lógica para verificar con la base de datos
        // Si existen, mostrar mensaje de error
    }

    // Método para guardar los datos del usuario en la base de datos
    private void saveUserToDatabase(String nombre, String apellido, String tipoDni, String dni, String usuario, String email, String contrasena) {
        // Lógica para guardar el usuario en la base de datos
    }
}
