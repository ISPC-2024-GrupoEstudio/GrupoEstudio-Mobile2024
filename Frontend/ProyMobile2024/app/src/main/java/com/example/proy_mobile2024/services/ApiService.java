package com.example.proy_mobile2024.services;
import com.example.proy_mobile2024.model.Perfil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    // 1. Solicitud de inicio de sesión (POST)
//    @POST("api/login/")
//    Call<Void> loginUser(@Body LoginData loginData);
      @GET("api/perfil/")
      Call<Perfil> getPerfil();
}
