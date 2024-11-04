package com.example.proy_mobile2024.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilRepository {

    private ApiService apiService;
    private Context context;

    public PerfilRepository(Context context){
        this.context = context;
        apiService = RetrofitClient.getInstance(context).getApiService();
    }

    public interface PerfilCallback{
        void enExito(List<UsuarioPerfil> perfiles);
        void enError(String mensajeError);
    }

    public void getPerfil(PerfilCallback callback){
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username -> ", null);

        if (username == null){
            callback.enError("No se encontro el username en Shared preferences");
            return;
        }

        Call<List<UsuarioPerfil>> call = apiService.getPerfil();
        call.enqueue(new Callback<List<UsuarioPerfil>>() {
            @Override
            public void onResponse(Call<List<UsuarioPerfil>> call, Response<List<UsuarioPerfil>> response){
                if (response.isSuccessful() && response.body() != null){
                    List<UsuarioPerfil> perfiles = response.body();
                    callback.enExito(perfiles);
                    callback.enExito(response.body());
                } else {
                    callback.enError("Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioPerfil>> call, Throwable t){
                callback.enError(t.getMessage());
            }
        });
    }
}
