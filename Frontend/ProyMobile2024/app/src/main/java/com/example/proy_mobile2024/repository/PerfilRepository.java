package com.example.proy_mobile2024.repository;

import com.example.proy_mobile2024.model.Perfil;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilRepository {

    private ApiService apiService;

    public PerfilRepository(){
        apiService = RetrofitClient.getInstance().getApiService();
    }

    public interface PerfilCallback{
        void enExito(Perfil perfil);
        void enError(String mensajeError);
    }

    public void getPerfil(PerfilCallback callback){
        Call<Perfil> call = apiService.getPerfil();
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response){
                if (response.isSuccessful() && response.body() != null){
                    callback.enExito(response.body());
                }else{
                    callback.enError("Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t){
                callback.enError(t.getMessage());
            }
        });
    }
}
