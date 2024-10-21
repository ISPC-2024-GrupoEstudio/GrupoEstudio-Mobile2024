package com.example.proy_mobile2024;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import android.util.Log;
import androidx.lifecycle.ViewModelProvider;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.auth0.android.Auth0;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.AuthCallback;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.viewsmodels.LoginViewModel;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.Authentication;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;
import com.example.proy_mobile2024.utils.Auth0Client;
import com.auth0.android.callback.Callback;
import com.auth0.android.result.Credentials;
import com.example.proy_mobile2024.viewsmodels.LoginViewModelFactor;

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
    private Auth0 auth0;
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
        auth0 = new Auth0(
                "AGgSJc6ahkAxIvjBbQ3vgrgZkKSHoc2N", // Client ID
                "dev-beq8aquv5nb4mfcn.us.auth0.com" // Domain
        );

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        etUsername = view.findViewById(R.id.user_id);
        etPassword = view.findViewById(R.id.contrasenalog);
        btnLogin = view.findViewById(R.id.loginButton);


        LoginViewModelFactor factory = new LoginViewModelFactor(getActivity().getApplication(), null);
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);



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

        iniciarSesion(username, password);
    }

    public void iniciarSesion(String username, String password) {
        AuthenticationAPIClient apiClient = new AuthenticationAPIClient(auth0);

        apiClient.login(username, password, "Mysql-Conector")
                .setScope("openid profile email") // Solicitar información básica del usuario
                .setAudience("https://mi-backend.com/api/") // Usar la audiencia de tu API Django
                .start(new Callback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials credentials) {
                        String jwtToken = credentials.getIdToken();

                        // Guardar token y enviarlo al backend para validar la sesión
                        guardarSesion(jwtToken);
                        enviarTokenAlBackend(jwtToken);

                        Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Log.e("Auth0", "Error en el inicio de sesión", exception);
                        Toast.makeText(getContext(), "Error: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
                                                /* loginViewModel.login(username, password);*

                                                 */
    private void guardarSesion(String jwtToken){
        SharedPreferences prefs = requireActivity().getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("jwtToken", jwtToken);
        editor.apply();
    }

    private void enviarTokenAlBackend(String token) {
        // Agrega el prefijo Bearer al token
        String bearerToken = "Bearer " + token;

        // Obtén la instancia de ApiService desde RetrofitClient
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        // Llama al método de validación de token
        Call<Void> call = apiService.validateToken(bearerToken); // Llama al método validateToken
        call.enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("auth_token", bearerToken);
                    editor.apply(); // Aplica los cambios

                    Toast.makeText(getActivity(), "Token validado en el backend", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Error al validar el token: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


