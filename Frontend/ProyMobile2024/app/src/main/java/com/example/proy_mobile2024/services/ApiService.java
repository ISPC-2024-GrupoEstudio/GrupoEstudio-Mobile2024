package com.example.proy_mobile2024.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    // 1. Solicitud de inicio de sesión (POST)
    @POST("api/login/")
    Call<Void> loginUser(@Body LoginData loginData);
}
