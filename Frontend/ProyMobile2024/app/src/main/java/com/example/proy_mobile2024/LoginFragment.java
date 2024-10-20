package com.example.proy_mobile2024;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.callback.Callback;
import com.auth0.android.authentication.AuthenticationException;
import com.example.proy_mobile2024.viewsmodels.LoginView;

public class LoginFragment extends Fragment {

    private Button btnLogin;
    private Auth0 auth0;

    public LoginFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        showAccessToken();
        btnLogin = view.findViewById(R.id.loginButton);

        auth0 = new Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain)
        );

        btnLogin.setOnClickListener(v -> loginWithAuth0());

        checkIfUserIsLoggedIn();

        return view;
    }

    private void loginWithAuth0() {
        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withScope("openid profile email")
                .withAudience("https://pet-boutique-api")
                .withRedirectUri("demo://dev-8sbfkudmbgtmvw2a.us.auth0.com/android/com.example.proy_mobile2024/callback")
                .start(requireActivity(), new Callback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials credentials) {
                        String accessToken = credentials.getAccessToken();
                        saveAccessToken(accessToken);
                        Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        loginUserWithToken(accessToken);
                        Log.d("AuthToken", "Token de acceso: " + accessToken);
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Toast.makeText(requireContext(), "Error en la autenticación: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void saveAccessToken(String accessToken) {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("access_token", accessToken);
        editor.apply();
    }

    private void checkIfUserIsLoggedIn() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("access_token", null);
        if (accessToken != null) {

            Toast.makeText(requireContext(), "Usuario ya logueado", Toast.LENGTH_SHORT).show();

        }
    }

    private void loginUserWithToken(String accessToken) {

        LoginView viewModel = new ViewModelProvider(this).get(LoginView.class);

        viewModel.loginWithToken(accessToken);

        viewModel.getLoginSuccess().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Log.d("LoginFragment", "Inicio de sesión exitoso");
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Log.d("LoginFragment", "Mostrando error: " + error);
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            } else {
                Log.d("LoginFragment", "Error desconocido");
                Toast.makeText(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show();
            }
        });

        // Opcional: Mostrar un loader mientras se procesa el login
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                // Mostrar un loader si está cargando
            } else {
                // Ocultar el loader
            }
        });
    }

    private String getAccessToken() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        return sharedPreferences.getString("access_token", null);  // Aquí obtienes el token
    }

    private void showAccessToken() {
        String token = getAccessToken();
        if (token != null) {
            Log.d("AuthToken", "Token de acceso: " + token);
        } else {
            Log.d("AuthToken", "No hay token guardado.");
        }
    }


    private void logout() {
        WebAuthProvider.logout(auth0)
                .withScheme("demo")
                .start(requireContext(), new Callback<Void, AuthenticationException>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        clearAccessToken();
                        Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        Toast.makeText(requireContext(), "No se pudo cerrar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAccessToken() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("access_token");
        editor.apply();
    }
}
