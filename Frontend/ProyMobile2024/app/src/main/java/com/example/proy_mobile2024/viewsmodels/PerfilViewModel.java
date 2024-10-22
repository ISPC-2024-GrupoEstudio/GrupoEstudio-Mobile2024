package com.example.proy_mobile2024.viewsmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proy_mobile2024.model.Perfil;
import com.example.proy_mobile2024.repository.PerfilRepository;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends ViewModel {

    private PerfilRepository perfilRespositorio;
    private MutableLiveData<Perfil> perfilLiveData;
    private MutableLiveData<Boolean> cargando = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public PerfilViewModel(){
        perfilLiveData = new MutableLiveData<>();
        perfilRespositorio = new PerfilRepository();

    }

    public LiveData<Perfil> getPerfilLiveData(){
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
            public void enExito(Perfil perfil){
                perfilLiveData.setValue(perfil);
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

