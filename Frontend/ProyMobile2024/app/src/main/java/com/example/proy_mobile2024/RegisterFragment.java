package com.example.proy_mobile2024;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proy_mobile2024.model.Usuario;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.services.RetrofitClientRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private EditText etNombre, etApellido, etDni, etUsuario, etEmail, etContrasena, etConfirmarContrasena;
    private Button btnRegistrar;
    private Spinner spinner_tipo_DNI;
    private int selected_tipo_DNI;

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
        spinner_tipo_DNI = view.findViewById(R.id.spinner_tipo_DNI);
        etDni = view.findViewById(R.id.dni);
        etUsuario = view.findViewById(R.id.et_username);
        etEmail = view.findViewById(R.id.et_email);
        etContrasena = view.findViewById(R.id.et_password);
        etConfirmarContrasena = view.findViewById(R.id.et_confirm_password);
        btnRegistrar = view.findViewById(R.id.btn_Registro);

        prepararSpinner();
        // Configurar el evento click del botón Registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtener los valores ingresados
                //String nombre = etNombre.getText().toString().trim();
                //String apellido = etApellido.getText().toString().trim();
                //int tipoDni = selected_tipo_DNI;
                //String dni = etDni.getText().toString().trim();
                //String usuario = etUsuario.getText().toString().trim();
                //String email = etEmail.getText().toString().trim();
                //String contrasena = etContrasena.getText().toString().trim();
                //String confirmarContrasena = etConfirmarContrasena.getText().toString().trim();

                String nombreUsuario = etUsuario.getText().toString().trim(); // nombre_usuario
                String nombre = etNombre.getText().toString().trim();         // nombre
                String apellido = etApellido.getText().toString().trim();     // apellido
                int numeroDocumento = Integer.parseInt(etDni.getText().toString().trim()); // numero_documento
                String email = etEmail.getText().toString().trim();           // email
                String password = etContrasena.getText().toString().trim();   // password
                String confirmarContrasena = etConfirmarContrasena.getText().toString().trim();
                String direccion = "Dirección"; // Debes obtener la dirección de algún campo, si la API lo requiere
                int telefono = 123456789;       // Número telefónico, asegúrate de obtenerlo desde un EditText si es necesario
                String fotoPerfil = "url_de_la_foto"; // Asegúrate de agregar esto si la API lo requiere
                int id_tipo_documento = selected_tipo_DNI;
                String dni = etDni.getText().toString().trim();
                int id_rol = 1;  // Definir un rol por defecto, si lo requiere tu API

                // Validaciones
                if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() ||
                        nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarContrasena.isEmpty()) {
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
                if (nombreUsuario.length() < MIN_USER_LENGTH) {
                    Toast.makeText(getActivity(), "El usuario debe tener al menos " + MIN_USER_LENGTH + " caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar longitud mínima de contraseña
                if (password.length() < MIN_PASSWORD_LENGTH) {
                    Toast.makeText(getActivity(), "La contraseña debe tener al menos " + MIN_PASSWORD_LENGTH + " caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que las contraseñas coinciden
                if (!password.equals(confirmarContrasena)) {
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aquí puedes llamar a tu API para verificar si el usuario o email ya existen
                checkUserOrEmailExists(nombreUsuario, email);  // Simulación de API para verificar

                // Registro exitoso
                //saveUserToDatabase(nombre, apellido, tipoDni, dni, usuario, email, contrasena);

                Usuario newUser = new Usuario(nombre, apellido, id_tipo_documento, Integer.parseInt(dni), nombreUsuario, email, password);
                ApiService apiService = RetrofitClientRegister.getApiService();

                Call<Usuario> call = apiService.registerUser(newUser);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error durante el registro de usuario, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                });



                // Simulación de registro exitoso
                //Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

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

    private void prepararSpinner() {
        // Crear un ArrayAdapter con la opción "DNI"
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, new String[]{"DNI"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al spinner
        spinner_tipo_DNI.setAdapter(adapter);

        // Preseleccionar la opción "DNI"
        spinner_tipo_DNI.setSelection(0);

        // Configurar un listener para manejar la selección
        spinner_tipo_DNI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Valor asociado a la selección
                int selectedValue = 1; // Valor que deseas usar
                selected_tipo_DNI = selectedValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
    }

}