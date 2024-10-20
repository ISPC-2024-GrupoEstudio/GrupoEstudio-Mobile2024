package com.example.proy_mobile2024.services;

import com.example.proy_mobile2024.model.LoginData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    // Método para iniciar sesión con usuario y contraseña
    @POST("api/login/") // Asegúrate de que este sea el endpoint correcto
    Call<Void> loginUser(@Body LoginData loginData);

    @POST("api/token/")
    Call<Void> loginWithToken(@Header("Authorization") String token);
}
