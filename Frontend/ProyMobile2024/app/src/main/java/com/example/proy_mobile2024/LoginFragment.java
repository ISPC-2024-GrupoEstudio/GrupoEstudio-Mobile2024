package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.callback.Callback;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.result.UserProfile;

public class LoginFragment extends Fragment {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Auth0 auth0;

    public LoginFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.user_id);
        etPassword = view.findViewById(R.id.contrasenalog);
        btnLogin = view.findViewById(R.id.loginButton);

        auth0 = new Auth0("sbIAeWFgwDvAziopRlM1gczznHltFHnK", "dev-8sbfkudmbgtmvw2a.us.auth0.com");

        // Configurar el listener del botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithAuth0();
            }
        });

        return view;
    }

    private void loginWithAuth0() {
        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withScope("openid profile email")
                .withAudience("https://pet-boutique-api")
                .withRedirectUri("demo://dev-8sbfkudmbgtmvw2a.us.auth0.com/android/com.example.proy_mobile2024/callback")
                .start(requireContext(), new Callback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials credentials) {
                        String accessToken = credentials.getAccessToken();
                        Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        showUserProfile(accessToken);
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Toast.makeText(requireContext(), "Error en la autenticación: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showUserProfile(String accessToken) {
        AuthenticationAPIClient client = new AuthenticationAPIClient(auth0);

        // Obtener la información del perfil del usuario con el token de acceso
        client.userInfo(accessToken)
                .start(new Callback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(UserProfile profile) {
                        String name = profile.getName();
                        String email = profile.getEmail();
                        // Mostrar la información del perfil
                        Toast.makeText(requireContext(), "Bienvenido " + name + "\n" + email, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        Toast.makeText(requireContext(), "Error al obtener el perfil: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void logout() {
        WebAuthProvider.logout(auth0)
                .withScheme("demo")
                .start(requireContext(), new Callback<Void, AuthenticationException>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // El usuario ha cerrado sesión
                        Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        Toast.makeText(requireContext(), "No se pudo cerrar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
