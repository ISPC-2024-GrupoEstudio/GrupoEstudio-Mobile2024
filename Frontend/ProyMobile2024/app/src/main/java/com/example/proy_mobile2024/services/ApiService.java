package com.example.proy_mobile2024.services;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;
import com.example.proy_mobile2024.model.LoginData;


public interface ApiService {
    // 1. Solicitud de inicio de sesión (POST)
    @POST("login/")
    Call<Void> loginUser(@Body LoginData loginData);
}
