package com.example.proy_mobile2024.services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientCorreo {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://correo-argentino1.p.rapidapi.com/";

    public static CorreoArgentinoApi getApi() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("x-rapidapi-host", "correo-argentino1.p.rapidapi.com")
                                    .header("x-rapidapi-key", "4794642c2emsh8eb5dc4233923a9p1406edjsneb98b17dbb9c")
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CorreoArgentinoApi.class);
    }
}
