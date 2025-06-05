package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class IntroCard2Fragment extends Fragment {

    private static final String TAG = "IntroCard2Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_introcard2, container, false);

        Button btnContinuar = view.findViewById(R.id.button_continue2);
        TextView txtLogin = view.findViewById(R.id.text_login);

        if (btnContinuar == null) {
            return view;
        }

        // Asegurar propiedades del botón
        btnContinuar.setClickable(true);
        btnContinuar.setEnabled(true);
        btnContinuar.setFocusable(true);

        // Usar lambda (más simple)
        btnContinuar.setOnClickListener(v -> {

            try {
                IntroduccionActivity activity = (IntroduccionActivity) requireActivity();
                activity.nextPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

            // Opcional: cambiar el color para que se vea que es clickeable
            txtLogin.setTextColor(getResources().getColor(R.color.purple));
        }

        return view;
    }
}