package com.example.proy_mobile2024.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitClient instance = null;
    private ApiService apiService;
    private static final String BASE_URL = "https://bda6-190-136-244-190.ngrok-free.app";

    private RetrofitClient() {
        // Inicializa la API service aquí
        retrofit = getClient();
        apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}

