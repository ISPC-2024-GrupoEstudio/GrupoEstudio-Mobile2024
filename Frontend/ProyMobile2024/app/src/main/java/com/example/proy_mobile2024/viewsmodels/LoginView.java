package com.example.proy_mobile2024.viewsmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginView extends ViewModel {

    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private final ApiService apiService;

    public LoginView() {
        apiService = RetrofitClient.getInstance().getApiService();
    }

    public void loginWithToken(String token) {
        isLoading.setValue(true);

        Call<Void> call = apiService.loginWithToken("Bearer " + token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                isLoading.setValue(false);

                Log.d("LoginView", "Código de respuesta: " + response.code());

                if (response.isSuccessful()) {
                    loginSuccess.setValue(true);
                } else {
                    loginSuccess.setValue(false);
                    if (response.errorBody() != null) {
                        try {
                            String errorBody = response.errorBody().string();
                            errorMessage.setValue("Error: " + errorBody);
                        } catch (IOException e) {
                            errorMessage.setValue("Error desconocido");
                            e.printStackTrace();
                        }
                    } else {
                        errorMessage.setValue("Error: " + response.message());
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                isLoading.setValue(false);

                loginSuccess.setValue(false);

                Log.e("LoginView", "Error de conexión: " + t.getMessage());
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}

