package com.example.proy_mobile2024.viewsmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactor implements ViewModelProvider.Factory {
    private Application application;
    private String token;

    public LoginViewModelFactor(Application application, String token) {
        this.application = application;
        this.token = token;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}