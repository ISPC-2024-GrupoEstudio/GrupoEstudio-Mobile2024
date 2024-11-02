package com.example.proy_mobile2024;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.TokenResponse;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.viewsmodels.LoginViewModel;
import com.example.proy_mobile2024.viewsmodels.LoguinViewModelFactory;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import java.net.HttpCookie;

import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private TextView tvContador;
    private CountDownTimer countDownTimer;
    private TextView tvFailedAttempts;
    private int failedAttempts = 0;
    private boolean isLocked = false;
    private long lockUntil = 0; // Timestamp para manejar el tiempo de bloqueo
    private static final String PREFS_NAME = "AuthPrefs";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean isPasswordVisible = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }
    private LoginViewModel loginViewModel;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private String nombreDeUsuario;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        isLocked = preferences.getBoolean("isLocked", false);
        lockUntil = preferences.getLong("lockUntil", 0);
        failedAttempts = preferences.getInt("failedAttempts", 0);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        LoguinViewModelFactory factory = new LoguinViewModelFactory(requireContext());
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        etUsername = view.findViewById(R.id.user_id);
        etPassword = view.findViewById(R.id.contrasenalog);
        btnLogin = view.findViewById(R.id.loginButton);

        ImageView imageViewTogglePassword = view.findViewById(R.id.imageViewTogglePassword);
        TextView registerPrompt = view.findViewById(R.id.registerPrompt);

        // Configurar el clic del botón de alternancia de visibilidad
        imageViewTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Ocultar la contraseña
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewTogglePassword.setImageResource(R.drawable.ic_eye_closed); // Cambia a ícono de ojo cerrado
                } else {
                    // Mostrar la contraseña
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewTogglePassword.setImageResource(R.drawable.ic_eye_open); // Cambia a ícono de ojo abierto
                }
                // Alternar el estado de visibilidad
                isPasswordVisible = !isPasswordVisible;

                // Mover el cursor al final del texto después de cambiar la visibilidad
                etPassword.setSelection(etPassword.getText().length());
            }
        });

        registerPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a RegisterFragment
                Fragment registerFragment = new RegisterFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, registerFragment) // R.id.fragment_container es el ID del contenedor de fragmentos
                        .addToBackStack(null) // Agrega a la pila para que se pueda volver atrás
                        .commit();
            }
        });

        tvContador = view.findViewById(R.id.tvContador);
        tvFailedAttempts = view.findViewById(R.id.failed_attempts);

        tvFailedAttempts = view.findViewById(R.id.failed_attempts); // Inicializa el TextView
        updateFailedAttemptsText(); // Muestra el conteo de intentos fallidos
        updateLockState();


        if (isLocked && System.currentTimeMillis() < lockUntil) {
            btnLogin.setEnabled(false); // Deshabilitar si todavía está bloqueado
            long remainingTime = lockUntil - System.currentTimeMillis();
            startLockTimer(remainingTime); // Iniciar temporizador de bloqueo
            updateFailedAttemptsText(); // Actualizar el texto de intentos fallidos
        } else {
            btnLogin.setEnabled(true); // Habilitar el botón si no está bloqueado
            failedAttempts = 0; // Reiniciar el contador de intentos fallidos al entrar
        }



        loginViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    // Login exitoso
                    Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    // Aquí puedes navegar a otra actividad o fragmento
                } else {
                    // Fallo en el inicio de sesión
                    Toast.makeText(getActivity(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if (message != null) {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Al volver al fragmento, verifica el estado de bloqueo
        updateLockState();
    }

    private void updateLockState() {
        long currentTime = System.currentTimeMillis();
        if (isLocked && System.currentTimeMillis() < lockUntil) {
            btnLogin.setEnabled(false); // Deshabilitar si todavía está bloqueado
            long remainingTime = lockUntil - currentTime;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            startLockTimer(remainingTime); // Iniciar temporizador de bloqueo
            tvFailedAttempts.setText("Bloqueado. Intenta nuevamente en " + (remainingTime / 60000) + " minutos.");
            updateFailedAttemptsText();
            } else if (currentTime >= lockUntil && isLocked) {
            // Si el tiempo de bloqueo ha expirado
            isLocked = false;
            btnLogin.setEnabled(true);
            failedAttempts = 0;
            saveFailedAttempts();
            saveLockState(false);
            if (tvContador != null) {
                tvContador.setVisibility(View.GONE);
            }
            if (tvFailedAttempts != null) {
                tvFailedAttempts.setVisibility(View.GONE);
            }

        } else {
            btnLogin.setEnabled(true); // Habilitar el botón si no está bloqueado
            if (tvContador != null) {
                tvContador.setVisibility(View.GONE);
            }
            if (tvFailedAttempts != null) {
                tvFailedAttempts.setVisibility(View.GONE);
            }
        }

    }



    private void bloquearBoton() {
        if (failedAttempts < 5) { // Aseguramos que no pase de 5
            failedAttempts++;
        }
        saveFailedAttempts();
        if (failedAttempts >= 5) { // Cambiamos de > a >=
            isLocked = true;
            lockUntil = System.currentTimeMillis() + 300000;
            saveLockState(true);
            updateLockState();
        }
    }

    private void saveFailedAttempts() {
        SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("failedAttempts", failedAttempts); // Guardar intentos fallidos
        editor.apply();
    }

    private void iniciarConteoRegresivo(int segundos) {
        tvContador.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(segundos * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int segundosRestantes = (int) (millisUntilFinished / 1000);
                int minutos = segundosRestantes / 60;
                int segundosFinales = segundosRestantes % 60;
                tvContador.setText(String.format("Tiempo restante: %d:%02d", minutos, segundosFinales));
            }

            @Override
            public void onFinish() {
                tvContador.setVisibility(View.GONE);
                btnLogin.setEnabled(true); // Habilitar el botón nuevamente
                failedAttempts = 0; // Reiniciar intentos fallidos
                saveLockState(false); // Reiniciar estado de bloqueo
                saveFailedAttempts(); // Reiniciar intentos fallidos
            }
        }.start();
    }


    private void updateFailedAttemptsText() {
        tvFailedAttempts.setText("Intentos fallidos: " + failedAttempts + " de 5");
        tvFailedAttempts.setVisibility(View.VISIBLE); // Asegúrate de que se muestre el TextView
    }


    private void validateLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validar que el campo username no esté vacío
        if (TextUtils.isEmpty(username)) {
            //etUsername.setError("El nombre de usuario es requerido");
            Toast.makeText(getActivity(), "Por favor, ingrese su nombre de usuario.", Toast.LENGTH_SHORT).show(); // Mensaje emergente
            return;
        }

        // Validar que el campo password no esté vacío
        if (TextUtils.isEmpty(password)) {
            //etPassword.setError("La contraseña es requerida");
            Toast.makeText(getActivity(), "Por favor, ingrese su contraseña.", Toast.LENGTH_SHORT).show(); // Mensaje emergente
            return;
        }

        // Validar la longitud de la contraseña (mínimo 8 caracteres)
        if (password.length() < 8) {
            //etPassword.setError("La contraseña debe tener al menos 8 caracteres");
            Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show(); // Mensaje emergente
            return;
        }


        loginUser(username, password);

    }

    private void saveLockState(boolean locked) {
        SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLocked", locked);
        editor.putLong("lockUntil", locked ? lockUntil : 0);
        editor.apply();
    }


    private void saveTokens(String token, String refresh, String id_usuario, String nombre, String apellido, String email, String username) {
        SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", token);
        editor.putString("refreshToken", refresh);
        editor.putString("id_usuario", id_usuario); // Si es un String, ajusta según tu modelo
        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putString("email", email);
        editor.putString("username", username);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        // Datos de depuración
        Log.d("TokenDebug", "Token guardado: " + token);
        Log.d("TokenRefreshDebug", "Refresh Token: " + refresh);
        Log.d("UserInfoDebug", "ID de usuario: " + id_usuario);
        Log.d("UserInfoDebug", "nombre: " + nombre);
        Log.d("UserInfoDebug", "apellido: " + apellido);
        Log.d("UserInfoDebug", "email: " + email);
    }

    private void showAlert(String title, String message) {
        // Utiliza requireContext() o getActivity() para obtener el contexto
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        // Crear y mostrar el AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loginUser(String username, String password) {
        if (isLocked) {
            long remainingTime = lockUntil - System.currentTimeMillis();
            if (remainingTime > 0) {
                showAlert("Bloqueado", "Has excedido el número de intentos. Intenta de nuevo en " + (remainingTime / 60000) + " minutos.");
                return; // Salir si está bloqueado
            } else {
                isLocked = false; // Desbloquear si el tiempo ha pasado
                tvContador.setText("");
                failedAttempts = 0; // Reiniciar el contador de intentos fallidos
                saveLockState(false);
            }
        }
        Log.d("LoginUser", "Iniciando el proceso de login");
        LoginData loginData = new LoginData(username, password); // Asegúrate de tener la clase LoginData creada
        Log.d("LoginUser", "Datos de login: " + loginData.getUsername() + ", " + loginData.getPassword());

        ApiService apiService = RetrofitClient.getInstance(getActivity()).getApiService();
        Call<TokenResponse> call = apiService.loginUser(loginData);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                // Añade esto en tu método onResponse de loginUser, justo después de guardar los tokens
                NavigationView navigationView = getActivity().findViewById(R.id.nav_view); // Asegúrate de que este ID sea correcto
                View headerView = navigationView.getHeaderView(0); // Obtiene la vista del encabezado
                TextView navUsername = headerView.findViewById(R.id.nav_header_title); // Asegúrate de que este ID sea correcto
                navUsername.setText(loginData.getUsername());

                Log.d("LoginUser", "Respuesta recibida del servidor");
                if (response.isSuccessful() && response.body() != null) {
                    failedAttempts = 0; // Reiniciar contador en caso de éxito
                    tvContador.setText("");
                    Log.d("LoginUser", "Login exitoso");
                    TokenResponse tokenResponse = response.body();
                    String token = response.body().getToken();
                    String refreshToken = response.body().getRefreshToken();

                    nombreDeUsuario = tokenResponse.getUsuario().getNombre(); // Guardar el nombre en la variable de instancia
                    Log.d("LoginUser", "Nombre de usuario obtenido: " + nombreDeUsuario);

                    String nombre = response.body().getUsuario().getNombre();
                    String apellido = response.body().getUsuario().getApellido();
                    String email = response.body().getUsuario().getEmail();
                    String id_usuario = response.body().getUsuario().getNombreUsuario();

                    // Guardar tokens y cualquier otro dato necesario en SharedPreferences
                    saveTokens(token, refreshToken, id_usuario, nombre, apellido, email, username);
                    ((MainActivity) getActivity()).checkLoginStatus();
                    // Redirigir a la actividad principal
                    //Intent intent = new Intent(getActivity(), LandingActivity.class);
                    // Puedes pasar datos adicionales si es necesario
                    // intent.putExtra("nombreUsuario", nombre);
                    //startActivity(intent);
                    // Redirigir al SobreNosotrosFragment
                    Fragment sobreNosotrosFragment = new SobreNosotrosFragment(); // Crea una instancia del fragmento
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, sobreNosotrosFragment); // Cambia 'fragment_container' por el ID de tu contenedor de fragmentos
                    transaction.addToBackStack(null); // Añadir a la pila de retroceso, si deseas poder volver al fragmento anterior
                    transaction.commit(); // Realiza la transacción
                } else {
                    Log.e("LoginError", "Error en el login: " + response.code());
                    SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLocked", isLocked);
                    editor.putLong("lockUntil", lockUntil);
                    editor.apply();

                    if (failedAttempts >= 4) {
                        failedAttempts = 5;
                        isLocked = true;
                        lockUntil = System.currentTimeMillis() + 5 * 60 * 1000; // Bloquear por 5 minutos
                        saveLockState(true);
                        bloquearBoton();
                        // Mostrar el mensaje de bloqueo
                        showAlert("Bloqueado", "Has excedido el número de intentos. Intenta de nuevo en 5 minutos.");
                        updateFailedAttemptsText();
                        return;
                    } else {
                        failedAttempts++;
                        updateFailedAttemptsText();
                        showAlert("Error", "Credenciales incorrectas. Intentos fallidos: " + failedAttempts);
                    }
                }



            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("LoginError", "Error de conexión: " + t.getMessage());
                // Mostrar mensaje de error en la UI
                showAlert("Error", "Error de conexión: " + t.getMessage());
            }
        });
    }

    private void startLockTimer(long remainingTime) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        tvContador.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (tvContador != null) {
                    long minutes = millisUntilFinished / 60000;
                    long seconds = (millisUntilFinished % 60000) / 1000;
                    tvContador.setText(String.format("Tiempo restante: %d:%02d", minutes, seconds));
                }
            }

            @Override
            public void onFinish() {
                if (isAdded() && getContext() != null) {
                    isLocked = false;
                    btnLogin.setEnabled(true);
                    failedAttempts = 0;
                    saveLockState(false);
                    saveFailedAttempts();
                    if (tvContador != null) {
                        tvContador.setVisibility(View.GONE);
                    }
                    if (tvFailedAttempts != null) {
                        tvFailedAttempts.setVisibility(View.GONE);
                    }
                }
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

}
