package com.example.proy_mobile2024.viewsmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.repository.PerfilRepository;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilViewModel extends AndroidViewModel {

    private PerfilRepository perfilRespositorio;
    private MutableLiveData<UsuarioPerfil> usuarioPerfil = new MutableLiveData<>();
    private MutableLiveData<Boolean> cargando = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application){
        super(application);

    }

    public LiveData<UsuarioPerfil> getUsuarioPerfil(){
        return usuarioPerfil;
    }

    public LiveData<Boolean> getCargando(){
        return cargando;
    }

    public LiveData<String> getMensajeError(){
        return mensajeError;
    }

    public void fetchPerfil(String email){
        cargando.setValue(true);
        RetrofitClient.getInstance(getApplication()).getApiService().getPerfil().enqueue(new Callback<List<UsuarioPerfil>>() {
            @Override
            public void onResponse(Call<List<UsuarioPerfil>> call, Response<List<UsuarioPerfil>> response){
                cargando.setValue(false);
                if (response.isSuccessful() && response.body() != null){
                    List<UsuarioPerfil> usuarios = response.body();
                    for (UsuarioPerfil usuario : usuarios){
                        if (usuario.getEmail().equals(email)){
                            usuarioPerfil.setValue(usuario);
                            return;
                        }
                    }
                    mensajeError.setValue("Usuario no encontrado");
                }else{
                    mensajeError.setValue("Error al obtener los datos");
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioPerfil>> call, Throwable t){
                cargando.setValue(false);
                mensajeError.setValue("Error -> " + t.getMessage());
            }
        });
        Log.e("entrando", "perfil");
    }
}

