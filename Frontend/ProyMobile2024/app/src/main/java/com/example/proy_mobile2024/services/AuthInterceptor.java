package com.example.proy_mobile2024.services;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.proy_mobile2024.model.TokenResponse;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthInterceptor implements Interceptor {
    private Context context;

    public AuthInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        SharedPreferences sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("accessToken", null);
        String refreshToken = sharedPreferences.getString("refreshToken", null);
        Request originalRequest = chain.request();
        String requestUrl = originalRequest.url().toString(); // Obtener la URL de la solicitud

        Request.Builder builder = originalRequest.newBuilder();

        // Verificar si la URL no es para el registro antes de añadir el token
        if (token != null && !requestUrl.contains("/register/")) { // O la URL específica de tu endpoint de registro
            builder.header("Authorization", "Bearer " + token);
        }

        Response response = chain.proceed(builder.build());

        // Si el token está expirado, usa el refresh token para obtener uno nuevo
        if (response.code() == 401 && refreshToken != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://2232-2803-9800-9883-45f5-a5a9-95e4-10c8-7eef.ngrok-free.app/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService authService = retrofit.create(ApiService.class);
            TokenRefreshRequest refreshRequest = new TokenRefreshRequest(refreshToken);
            Call<TokenResponse> refreshCall = authService.refreshToken(refreshRequest);

            retrofit2.Response<TokenResponse> refreshResponse = refreshCall.execute();
            if (refreshResponse.isSuccessful() && refreshResponse.body() != null) {
                TokenResponse newTokenResponse = refreshResponse.body();
                String newToken = newTokenResponse.getToken();

                // Guardar el nuevo token en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("accessToken", newToken);
                editor.putString("refreshToken", newTokenResponse.getRefreshToken()); // También puedes actualizar el refresh token
                editor.apply();

                Request newRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + newToken)
                        .build();

                response.close(); // Cierra la respuesta anterior antes de hacer otra llamada
                return chain.proceed(newRequest);
            } else {
                // Manejar el caso en que el refresh token no es válido
                // Por ejemplo, limpiar los tokens de SharedPreferences y redirigir al usuario a la pantalla de inicio de sesión
            }
        }

        return response;
    }
}
