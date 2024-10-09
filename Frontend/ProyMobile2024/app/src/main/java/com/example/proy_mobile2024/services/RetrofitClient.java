package com.example.proy_mobile2024.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitClient instance = null;
    private ApiService apiService;
    private static final String BASE_URL = "http://192.168.0.99:8000/api/";;

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

