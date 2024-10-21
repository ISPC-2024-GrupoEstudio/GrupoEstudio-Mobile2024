package com.example.proy_mobile2024.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitClient instance;
    private ApiService apiService;
    private String jwtToken;
    private static final String BASE_URL = "https://ae5a-190-136-244-190.ngrok-free.app/api/";


    private RetrofitClient() {
        // Inicializa la API service aquí
        retrofit = getClient(BASE_URL);
        apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
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

