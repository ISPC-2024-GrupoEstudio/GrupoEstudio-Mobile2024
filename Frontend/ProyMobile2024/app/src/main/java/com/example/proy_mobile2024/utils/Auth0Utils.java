package com.example.proy_mobile2024.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.auth0.android.Auth0;
import com.auth0.android.jwt.JWT;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.callback.Callback;
import com.auth0.android.authentication.AuthenticationException;
import com.example.proy_mobile2024.MainActivity;

import java.util.List;

public class Auth0Utils {

    private static Auth0 auth0;

    public static void initialize(Context context) {
        auth0 = new Auth0(context);
    }

    public static void login(Context context) {
        WebAuthProvider.login(auth0)
                .withScheme("https")
                .withAudience("https://pet-boutique-api")
                .start(context, new AuthCallback(context));
    }

    public static boolean hasPermission(String token, String requiredPermission) {
        JWT jwt = new JWT(token);
        List<String> permissions = jwt.getClaim("permissions").asList(String.class);

        return permissions != null && permissions.contains(requiredPermission);
    }

    public static class AuthCallback implements Callback<Credentials, AuthenticationException> {
        private Context context;

        public AuthCallback(Context context) {
            this.context = context;
        }

        @Override
        public void onSuccess(Credentials credentials) {
            String token = credentials.getIdToken(); // Obtener el token JWT
            Log.d("Auth0", "Token: " + token);

            // Guardar el token en SharedPreferences u otro almacenamiento seguro
            context.getSharedPreferences("app", Context.MODE_PRIVATE).edit().putString("id_token", token).apply();

            // Redirigir al usuario a la actividad principal
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Asegurarse de que el intent se inicie correctamente
            context.startActivity(intent);
        }

        @Override
        public void onFailure(AuthenticationException exception) {
            Log.e("Auth0", "Error during authentication", exception);
        }
    }
}
