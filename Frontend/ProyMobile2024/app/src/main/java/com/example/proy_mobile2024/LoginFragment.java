package com.example.proy_mobile2024;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.SessionManager;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.viewsmodels.LoginViewModel;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }
    private LoginViewModel loginViewModel;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Verificar si el usuario ya está logueado
        if (SessionManager.getInstance().isLoggedIn()) {
            // Mostrar un Toast y regresar, evitando mostrar el login
            Toast.makeText(getActivity(), "Ya has iniciado sesión", Toast.LENGTH_SHORT).show();
            // Aquí podrías redirigir al usuario a otra parte si prefieres:
            // replaceFragment(new HomeFragment()); // o la pantalla que desees mostrar
            return view;  // Detener la ejecución del código si ya está logueado
        }

        etUsername = view.findViewById(R.id.user_id);
        etPassword = view.findViewById(R.id.contrasenalog);
        btnLogin = view.findViewById(R.id.loginButton);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    // Login exitoso
                    Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    // Aquí puedes navegar a otra actividad o fragmento
                } else {
                    // Fallo en el inicio de sesión
                    Toast.makeText(getActivity(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if (message != null) {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });
        return view;
    }

    private void validateLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validar que el campo username no esté vacío
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("El nombre de usuario es requerido");
            return;
        }

        // Validar que el campo password no esté vacío
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("La contraseña es requerida");
            return;
        }

        // Validar la longitud de la contraseña (mínimo 8 caracteres)
        if (password.length() < 8) {
            etPassword.setError("La contraseña debe tener al menos 8 caracteres");
            return;
        }


        loginViewModel.login(username, password);

    }
}
