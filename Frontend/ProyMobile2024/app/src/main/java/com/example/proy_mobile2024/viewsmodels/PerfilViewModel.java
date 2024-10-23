package com.example.proy_mobile2024.viewsmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proy_mobile2024.model.UsuarioPerfil;
import com.example.proy_mobile2024.repository.PerfilRepository;

import java.util.List;


public class PerfilViewModel extends ViewModel {

    private PerfilRepository perfilRespositorio;
    private MutableLiveData<UsuarioPerfil> perfilLiveData;
    private MutableLiveData<Boolean> cargando = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public PerfilViewModel(Context context){
        perfilLiveData = new MutableLiveData<>();
        perfilRespositorio = new PerfilRepository(context);

    }

    public LiveData<UsuarioPerfil> getPerfilLiveData(){
        return perfilLiveData;
    }

    public LiveData<Boolean> getCargando(){
        return cargando;
    }

    public LiveData<String> getMensajeError(){
        return mensajeError;
    }

    public void fetchPerfil(){
        cargando.setValue(true);
        perfilRespositorio.getPerfil(new PerfilRepository.PerfilCallback(){
            @Override
            public void enExito(List<UsuarioPerfil> perfiles){
                if (perfiles != null && !perfiles.isEmpty()){
                    perfilLiveData.setValue(perfiles.get(0));
                }else {
                    perfilLiveData.setValue(null);
                }
                cargando.setValue(false);
            }

            @Override
            public void enError(String errorMensaje){
                mensajeError.setValue(errorMensaje);
                cargando.setValue(false);
            }
        });
        Log.e("entrando", "perfil");
    }
}

