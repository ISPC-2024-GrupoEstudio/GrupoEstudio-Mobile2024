package com.example.proy_mobile2024.viewsmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginSuccess;
    private ApiService apiService;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<String> errorMessage;

    public LoginViewModel() {
        loginSuccess = new MutableLiveData<>();
        apiService = RetrofitClient.getInstance().getApiService();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    // Método para hacer login
    public void login(String username, String password) {
        isLoading.setValue(true); // Indica que la carga ha comenzado
        LoginData loginData = new LoginData(username, password);
        Call<Void> call = apiService.loginUser(loginData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                isLoading.setValue(false); // Finaliza el estado de carga
                if (response.isSuccessful()) {
                    loginSuccess.setValue(true);  // Login exitoso
                } else {
                    loginSuccess.setValue(false); // Fallo en el login
                    errorMessage.setValue("Error: " + response.message()); // Mensaje de error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                isLoading.setValue(false); // Finaliza el estado de carga
                loginSuccess.setValue(false); // Error en la conexión
                errorMessage.setValue("Error de conexión: " + t.getMessage()); // Mensaje de error
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

    public void setLoginSuccess(boolean success) {
        loginSuccess.setValue(success);
    }
}
