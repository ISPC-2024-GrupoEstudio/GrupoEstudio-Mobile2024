package com.example.proy_mobile2024;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.proy_mobile2024.LoginFragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
    private Spinner spinner_tipo_DNI;
    private Button btnRegistrar;
    private int selected_tipo_DNI = 0;

    // Constantes para validaciones
    private static final int MIN_USER_LENGTH = 5; // Mínimo de 5 caracteres para el usuario
    private static final int MAX_USER_LENGTH = 12;
    private static final int MIN_PASSWORD_LENGTH = 8; // Mínimo de 8 caracteres para la contraseña

    private boolean isPasswordVisible = false;  // Estado de visibilidad de la contraseña
    private boolean isConfirmPasswordVisible = false; // Estado de visibilidad de la confirmación de contraseña



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
        ImageView imageViewPassword = view.findViewById(R.id.imageViewPassword);
        ImageView imageViewConfirmPassword = view.findViewById(R.id.imageViewConfirmPassword);
        TextView ingresaRegister = view.findViewById(R.id.ingresa_register);


        // Listener para el icono de toggle de la contraseña
        imageViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Ocultar la contraseña
                    etContrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewPassword.setImageResource(R.drawable.ic_eye_closed); // Cambia a ícono de ojo cerrado
                } else {
                    // Mostrar la contraseña
                    etContrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewPassword.setImageResource(R.drawable.ic_eye_open); // Cambia a ícono de ojo abierto
                }
                // Alternar el estado de visibilidad
                isPasswordVisible = !isPasswordVisible;

                // Mover el cursor al final del texto después de cambiar la visibilidad
                etContrasena.setSelection(etContrasena.getText().length());
            }
        });

        ingresaRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a RegisterFragment
                Fragment loginFragmentt = new LoginFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragmentt) // R.id.fragment_container es el ID del contenedor de fragmentos
                        .addToBackStack(null) // Agrega a la pila para que se pueda volver atrás
                        .commit();
            }
        });

        // Listener para el icono de toggle de la confirmación de contraseña
        imageViewConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    // Ocultar la confirmación de la contraseña
                    etConfirmarContrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewConfirmPassword.setImageResource(R.drawable.ic_eye_closed); // Cambia a ícono de ojo cerrado
                } else {
                    // Mostrar la confirmación de la contraseña
                    etConfirmarContrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewConfirmPassword.setImageResource(R.drawable.ic_eye_open); // Cambia a ícono de ojo abierto
                }
                // Alternar el estado de visibilidad
                isConfirmPasswordVisible = !isConfirmPasswordVisible;

                // Mover el cursor al final del texto después de cambiar la visibilidad
                etConfirmarContrasena.setSelection(etConfirmarContrasena.getText().length());
            }
        });


        // TextWatcher para el campo de Usuario
        etUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sanitized = sanitizeInput(s.toString(), "[^a-zA-Z0-9]");
                if (!sanitized.equals(s.toString())) {
                    etUsuario.setText(sanitized);
                    etUsuario.setSelection(sanitized.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // TextWatcher para el campo de Nombre
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sanitized = sanitizeInput(s.toString(), "[^a-zA-Z ]");
                if (!sanitized.equals(s.toString())) {
                    etNombre.setText(sanitized);
                    etNombre.setSelection(sanitized.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // TextWatcher para el campo de Apellido
        etApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sanitized = sanitizeInput(s.toString(), "[^a-zA-Z ]");
                if (!sanitized.equals(s.toString())) {
                    etApellido.setText(sanitized);
                    etApellido.setSelection(sanitized.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // TextWatcher para el campo de DNI (solo números)
        etDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sanitized = sanitizeInput(s.toString(), "[^0-9]");
                if (!sanitized.equals(s.toString())) {
                    etDni.setText(sanitized);
                    etDni.setSelection(sanitized.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // TextWatcher para el campo de Email (dejar caracteres válidos en emails)
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sanitized = sanitizeInput(s.toString(), "[^a-zA-Z0-9@._-]");
                if (!sanitized.equals(s.toString())) {
                    etEmail.setText(sanitized);
                    etEmail.setSelection(sanitized.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        prepararSpinner();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreUsuario = etUsuario.getText().toString().trim();
                String nombre = etNombre.getText().toString().trim();
                String apellido = etApellido.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etContrasena.getText().toString().trim();
                String confirmarContrasena = etConfirmarContrasena.getText().toString().trim();
                int id_tipo_documento = selected_tipo_DNI;
                String dni = etDni.getText().toString().trim();

                if (dni.isEmpty()) {
                    Toast.makeText(getActivity(), "El campo DNI es obligatorio", Toast.LENGTH_LONG).show();
                    return; // Salir del método si el campo está vacío
                }

                // Validar que se haya seleccionado un tipo de DNI
                if (id_tipo_documento == 0) {  // 0 significa no seleccionado o default
                    Toast.makeText(getActivity(), "Por favor, seleccione un tipo de DNI", Toast.LENGTH_LONG).show();
                    return; // No continuar con el registro
                }

                // Validaciones
                if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() ||
                        nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarContrasena.isEmpty()) {
                    Toast.makeText(getActivity(), "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
                    return;                }

                // Validar DNI numérico
                if (!dni.matches("\\d+")) {
                    Toast.makeText(getActivity(), "El DNI debe contener solo números", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar longitud del DNI
                if (dni.length() != 8) { // Asumiendo que el DNI tiene 8 dígitos
                    Toast.makeText(getActivity(), "El DNI debe tener 8 dígitos", Toast.LENGTH_LONG).show();
                    return;
                }


                // Validar formato de email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getActivity(), "Email no válido", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar longitud de usuario (mínima y máxima)
                if (nombreUsuario.length() < MIN_USER_LENGTH || nombreUsuario.length() > MAX_USER_LENGTH) {
                    Toast.makeText(getActivity(), "El nombre de usuario debe tener entre " + MIN_USER_LENGTH + " y " + MAX_USER_LENGTH + " caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que el nombre de usuario solo contenga letras y números
                if (!nombreUsuario.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(getActivity(), "El nombre de usuario solo puede contener letras y números", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar longitud máxima de usuario (12 caracteres)
                if (nombreUsuario.length() > 12) {
                    Toast.makeText(getActivity(), "El nombre de usuario no puede tener más de 12 caracteres", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar longitud mínima de contraseña
                if (password.length() < MIN_PASSWORD_LENGTH) {
                    Toast.makeText(getActivity(), "La contraseña debe tener al menos " + MIN_PASSWORD_LENGTH + " caracteres", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar que la contraseña cumpla con los requisitos de complejidad
                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                if (!password.matches(passwordPattern)) {
                    Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validar que las contraseñas coinciden
                if (!password.equals(confirmarContrasena)) {
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }

                // Aquí puedes llamar a tu API para verificar si el usuario o email ya existen
                checkUserOrEmailExists(nombreUsuario, email);  // Simulación de API para verificar


                Usuario newUser = new Usuario(nombre, apellido, id_tipo_documento, Integer.parseInt(dni), nombreUsuario, email, password);
                ApiService apiService = RetrofitClientRegister.getApiService();

                Call<Usuario> call = apiService.registerUser(newUser);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Error, datos invalidos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error durante el registro de usuario, intente nuevamente", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }

    private String sanitizeInput(String input, String regex) {
        return input.replaceAll(regex, "");
    }


    // Método para verificar si el usuario o email ya existen (Simulación)
    private void checkUserOrEmailExists(String username, String email) {

    }

    // Método para guardar los datos del usuario en la base de datos
    private void saveUserToDatabase(String nombre, String apellido, String tipoDni, String dni, String usuario, String email, String contrasena) {
        // Lógica para guardar el usuario en la base de datos
    }

    private void prepararSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_item,
                getResources().getStringArray(R.array.tipos_dni));

        adapter.setDropDownViewResource(R.layout.spinner_item);

        spinner_tipo_DNI.setAdapter(adapter);

        spinner_tipo_DNI.setSelection(0);

        spinner_tipo_DNI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    selected_tipo_DNI = 0;
                } else {
                    selected_tipo_DNI = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selected_tipo_DNI = 0;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerDni = view.findViewById(R.id.spinner_tipo_DNI);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                requireContext(),
                R.layout.spinner_item,
                getResources().getStringArray(R.array.tipos_dni)
        ) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerDni.setAdapter(adapter);
    }
}