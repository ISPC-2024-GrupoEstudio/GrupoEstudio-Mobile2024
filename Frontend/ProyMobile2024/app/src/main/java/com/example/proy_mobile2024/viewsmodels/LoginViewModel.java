package com.example.proy_mobile2024.viewsmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.TokenResponse;
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

    public LoginViewModel(Context context) {
        loginSuccess = new MutableLiveData<>();
        apiService = RetrofitClient.getInstance(context).getApiService();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    // Método para hacer login
    public void login(String username, String password) {
        isLoading.setValue(true); // Indica que la carga ha comenzado
        LoginData loginData = new LoginData(username, password);
        Call<TokenResponse> call = apiService.loginUser(loginData);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                isLoading.setValue(false); // Indica que la carga ha terminado
                if (response.isSuccessful() && response.body() != null) {
                    TokenResponse tokenResponse = response.body();
                    // Manejar el token aquí (guardarlo, usarlo, etc.)
                    String accessToken = tokenResponse.getToken(); // Obtener el token de acceso
                    // Aquí puedes guardar el token en SharedPreferences o en otro lugar
                } else {
                    // Manejar el error de inicio de sesión
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                isLoading.setValue(false); // Indica que la carga ha terminado
                // Manejar el error de conexión o de la llamada
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
