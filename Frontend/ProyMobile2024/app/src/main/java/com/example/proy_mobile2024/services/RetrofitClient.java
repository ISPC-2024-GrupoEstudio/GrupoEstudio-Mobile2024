package com.example.proy_mobile2024.services;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit = null;
    private static RetrofitClient instance = null;
    private ApiService apiService;
    private static final String BASE_URL = "https://98f1-181-111-12-240.ngrok-free.app/api/"; // "https://127.0.0.1/api/";


    private RetrofitClient(Context context) {
        // Crear el interceptor y pasar el contexto
        AuthInterceptor authInterceptor = new AuthInterceptor(context);

        // Crear el cliente OkHttp y añadir el interceptor
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build();

        // Crear la instancia de Retrofit usando el cliente OkHttp
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient) // Usar el cliente OkHttp configurado
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
