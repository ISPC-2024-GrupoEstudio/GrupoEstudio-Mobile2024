package com.example.proy_mobile2024.services;
import com.example.proy_mobile2024.model.LoginData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/api/auth/login/")
    Call<Void> loginUser(@Body LoginData loginData);
}
