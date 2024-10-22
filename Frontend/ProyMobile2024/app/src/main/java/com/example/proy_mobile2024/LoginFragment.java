package com.example.proy_mobile2024;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
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
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import java.net.HttpCookie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }
    private LoginViewModel loginViewModel;
    private EditText etUsername, etPassword;
    private Button btnLogin;
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

    private void validateLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validar que el campo username no esté vacío
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("El nombre de usuario es requerido");
            return;
        }

        // Validar que el campo password no esté vacío
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("La contraseña es requerida");
            return;
        }

        // Validar la longitud de la contraseña (mínimo 8 caracteres)
        if (password.length() < 8) {
            etPassword.setError("La contraseña debe tener al menos 8 caracteres");
            return;
        }


        loginUser(username, password);

    }


    private void saveTokens(String token, String refresh, String id_usuario, String nombre, String apellido, String email) {
        SharedPreferences preferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", token);
        editor.putString("refreshToken", refresh);
        editor.putString("id_usuario", id_usuario); // Si es un String, ajusta según tu modelo
        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putString("email", email);

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
        Log.d("LoginUser", "Iniciando el proceso de login");
        LoginData loginData = new LoginData(username, password); // Asegúrate de tener la clase LoginData creada
        Log.d("LoginUser", "Datos de login: " + loginData.getUsername() + ", " + loginData.getPassword());

        ApiService apiService = RetrofitClient.getInstance(getActivity()).getApiService();
        Call<TokenResponse> call = apiService.loginUser(loginData);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                Log.d("LoginUser", "Respuesta recibida del servidor");
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("LoginUser", "Login exitoso");
                    TokenResponse tokenResponse = response.body();
                    String token = response.body().getToken();
                    String refreshToken = response.body().getRefreshToken();

                    String nombre = response.body().getUsuario().getNombre();
                    String apellido = response.body().getUsuario().getApellido();
                    String email = response.body().getUsuario().getEmail();
                    String id_usuario = response.body().getUsuario().getNombreUsuario();

                    // Guardar tokens y cualquier otro dato necesario en SharedPreferences
                    saveTokens(token, refreshToken, id_usuario, nombre, apellido, email);

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
                    // Manejar errores de respuesta
                    showAlert("Error", "Credenciales incorrectas");
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
}
