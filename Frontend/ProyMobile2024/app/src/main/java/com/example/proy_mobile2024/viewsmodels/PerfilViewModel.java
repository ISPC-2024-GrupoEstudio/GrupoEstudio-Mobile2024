package com.example.proy_mobile2024.viewsmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.repository.PerfilRepository;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilViewModel extends AndroidViewModel {

    private final Application application;
    private final MutableLiveData<UsuarioPerfil> usuarioPerfil = new MutableLiveData<>();
    private final MutableLiveData<Boolean> cargando = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> actualizacionExitosa = new MutableLiveData<>();
    private boolean isActualizacionFoto = false;

    public PerfilViewModel(@NonNull Application application){
        super(application);
        this.application = application;
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

    public LiveData<Boolean> getActualizacionExitosa(){ return actualizacionExitosa; }

    public void setActualizacionFoto(boolean isActualizacionFoto){ this.isActualizacionFoto = isActualizacionFoto; }

    public boolean isActualizacionFoto(){ return isActualizacionFoto; }

    public void resetActualizacionExitosa(){ actualizacionExitosa.setValue(null); }

    public void fetchPerfil(String username){
        cargando.setValue(true);
        RetrofitClient.getInstance(getApplication()).getApiService().getPerfil().enqueue(new Callback<List<UsuarioPerfil>>() {
            @Override
            public void onResponse(Call<List<UsuarioPerfil>> call, Response<List<UsuarioPerfil>> response){
                cargando.setValue(false);
                if (response.isSuccessful() && response.body() != null){
                    List<UsuarioPerfil> usuarios = response.body();
                    for (UsuarioPerfil usuario : usuarios){
                        if (usuario.getUser_name().equals(username)){
                            usuarioPerfil.setValue(usuario);
                            return;
                        }
                    }
                    mensajeError.setValue("Usuario no encontrado");
                }else{
                    mensajeError.setValue("Error al obtener los datos ");
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

    public void actualizarPerfil(String nombreUsuario, UsuarioPerfil perfilActualizado){
        if (perfilActualizado == null || nombreUsuario == null){
            mensajeError.setValue("Datos de perfil inválidos");
            return;
        }

        cargando.setValue(true);
        RetrofitClient.getInstance(null).getApiService()
                .actualizarPerfil(nombreUsuario, perfilActualizado)
                .enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                cargando.setValue(false);
                if (response.isSuccessful()){
                    usuarioPerfil.setValue(perfilActualizado);
                    actualizacionExitosa.setValue(true);
                    mensajeError.setValue(null);
                }else{
                    actualizacionExitosa.setValue(false);
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Error desconocido";
                        mensajeError.setValue("Error al actualizar el perfil: " + errorBody);
                        Log.d("PerfilViewModel", errorBody);
                    } catch (IOException e){
                        mensajeError.setValue("Error al actualizar el perfil: " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                cargando.setValue(false);
                actualizacionExitosa.setValue(false);
                mensajeError.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

}

