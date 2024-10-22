package com.example.proy_mobile2024.services;
import com.example.proy_mobile2024.model.LoginResponse;
import com.example.proy_mobile2024.model.Producto;

import java.util.List;

import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
//    // 1. Solicitud de inicio de sesión (POST)
//    Call<Void> loginUser(@Body LoginData loginData);

    //2.solicitud de productos para galería (GET)
    @GET("productos")
    Call<List<Producto>> obtenerProductos();

    @POST("auth/login/")
    Call<LoginResponse> loginUser(@Body LoginData loginData);

    @POST("auth/register/")
    Call<Usuario> registerUser(@Body Usuario usuario);

}

