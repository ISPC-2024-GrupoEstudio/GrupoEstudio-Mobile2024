package com.example.proy_mobile2024.viewsmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.LoginResponse;
import com.example.proy_mobile2024.model.SessionManager;
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
        isLoading.setValue(true);
        LoginData loginData = new LoginData(username, password);
        Call<LoginResponse> call = apiService.loginUser(loginData);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    // Almacena el token en la clase SessionManager
                    String token = response.body().getToken();
                    SessionManager.getInstance().saveToken(token);
                    loginSuccess.setValue(true);
                } else {
                    loginSuccess.setValue(false);
                    errorMessage.setValue("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isLoading.setValue(false);
                loginSuccess.setValue(false);
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

    public boolean isUserLoggedIn() {
        //return SessionManager.getInstance().isUserLoggedIn(); // Verifica si el usuario está logueado
        LoginViewModel loginViewModel = new LoginViewModel(); // Asegúrate de que esto esté correctamente inicializado
        return loginViewModel.isUserLoggedIn();
    }

    public String getToken() {
        return SessionManager.getInstance().getToken(); // Obtiene el token
    }
}
