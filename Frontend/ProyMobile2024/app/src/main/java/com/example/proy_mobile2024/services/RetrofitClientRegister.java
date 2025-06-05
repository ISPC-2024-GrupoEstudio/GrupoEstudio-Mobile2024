package com.example.proy_mobile2024.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientRegister {

<<<<<<< HEAD
    private static final String BASE_URL = "https://a20c-2803-9800-9883-45f5-6826-bc86-a2d5-5669.ngrok-free.app/api/"; // Cambia esto por la URL de tu API
=======
    private static final String BASE_URL = "https://536b-181-92-31-235.ngrok-free.app/api/"; // Cambia esto por la URL de tu API
>>>>>>> 1d13caef7e37d341c40148b0e55aacfc27445d77
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getInstance().create(ApiService.class);
    }
}

