package com.example.proy_mobile2024;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class IntroCard3Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_introcard3, container, false);
        TextView txtLogin = view.findViewById(R.id.text_login);

        Button btnComenzar = view.findViewById(R.id.button_start);
        btnComenzar.setOnClickListener(v -> {
            ((IntroduccionActivity) requireActivity()).goToMain();
        });

        //Configurar click en texto de login
        if (txtLogin != null) {
            txtLogin.setClickable(true);
            txtLogin.setOnClickListener(v -> {
                Log.d(TAG, "*** CLICK EN TEXTO LOGIN ***");

                try {
                    // Llamar al método showLogin de la actividad padre
                    IntroduccionActivity activity = (IntroduccionActivity) requireActivity();
                    activity.showLogin();

                } catch (Exception e) {
                    Log.e(TAG, "Error al mostrar login", e);
                    Toast.makeText(getContext(), "Error al abrir login", Toast.LENGTH_SHORT).show();
                }
            });

            txtLogin.setTextColor(getResources().getColor(R.color.purple));
        }
        return view;
    }
}
