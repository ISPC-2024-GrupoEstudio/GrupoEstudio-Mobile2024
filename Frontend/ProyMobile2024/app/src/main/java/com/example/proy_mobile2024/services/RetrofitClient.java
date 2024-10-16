package com.example.proy_mobile2024.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit = null;
    private static RetrofitClient instance = null;
    private ApiService apiService;
    private static final String BASE_URL = "https://ae5a-190-136-244-190.ngrok-free.app/api/";


    private RetrofitClient()
    {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
                    apiService=retrofit.create(ApiService.class);
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

