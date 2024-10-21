package com.example.proy_mobile2024.viewsmodels;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import android.content.Context;
import retrofit2.Response;
import androidx.annotation.NonNull;
import android.app.Application;
import com.example.proy_mobile2024.model.Credentials;


public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> loginSuccess;
    private ApiService apiService;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<String> errorMessage;
    private Application application;
    private String jwtToken;

    public LoginViewModel(Application application) {
        super(application); // Llama al constructor de la superclase
        apiService = RetrofitClient.getInstance().getApiService();
        loginSuccess = new MutableLiveData<>();
        apiService = RetrofitClient.getInstance().getApiService();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    private void guardarSesion(String jwtToken) {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth_token", jwtToken); // Guarda el token con una clave específica
        editor.apply(); // Aplica los cambios
    }

    // Método para hacer login
    public void login(String username, String password) {
        isLoading.setValue(true); // Indica que la carga ha comenzado
        LoginData loginData = new LoginData(username, password);
        Call<Credentials> call = apiService.login(loginData);
        call.enqueue(new Callback<Credentials>() {
            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {
                isLoading.setValue(false); // Finaliza el estado de carga
                if (response.isSuccessful()) {
                    Credentials credentials = response.body();
                    String jwtToken = credentials.getIdToken(); // Asegúrate de que tu clase Credentials tenga este método
                   guardarSesion(jwtToken); // Llama a tu método para guardar el token
                    loginSuccess.setValue(true);  // Login exitoso
                } else {
                    loginSuccess.setValue(false); // Fallo en el login
                    errorMessage.setValue("Error: " + response.message()); // Mensaje de error
                }
            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {
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
