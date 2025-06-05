package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proy_mobile2024.adapter.IntroAdapter;

public class IntroduccionActivity extends AppCompatActivity {

    private static final String TAG = "IntroduccionActivity";
    private ViewPager2 viewPager;
    private IntroAdapter adapter;
    private boolean isShowingLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion);

        viewPager = findViewById(R.id.viewPager);
        adapter = new IntroAdapter(this);
        viewPager.setAdapter(adapter);


        //Transicion entre paginas con efecto más lento
        viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(android.view.View page, float position){
                // Hacer la transición más gradual
                float absPosition = Math.abs(position);

                if (absPosition <= 1) {
                    // Animación de fade más suave
                    page.setAlpha(1 - absPosition * 0.3f);

                    // Animación de escala más gradual
                    float scale = 0.9f + (1 - absPosition) * 0.1f;
                    page.setScaleX(scale);
                    page.setScaleY(scale);

                    // Opcional: ligero desplazamiento vertical
                    page.setTranslationY(-absPosition * 50);
                }
            }
        });

    }

    public void nextPage(){
        int currentItem = viewPager.getCurrentItem();
        int nextItem = currentItem + 1;
        int totalItems = adapter.getItemCount();


        if (nextItem < totalItems) {
            viewPager.setCurrentItem(nextItem, true); // true para animación suave
        }
    }

    public void goToMain(){
        Log.d(TAG, "goToMain() llamado");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    // NUEVO MÉTODO: Para mostrar el LoginFragment
    public void showLogin(){
        Log.d(TAG, "showLogin() llamado");

        if (!isShowingLogin) {
            // Ocultar el ViewPager
            viewPager.setVisibility(View.GONE);

            // Mostrar el contenedor del login
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

            // Crear y mostrar el LoginFragment
            LoginFragment loginFragment = new LoginFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, loginFragment)
                    .addToBackStack("login")
                    .commit();

            isShowingLogin = true;
            View container = findViewById(R.id.fragment_container);
            Log.d(TAG, "Container found: " + (container != null));
        }
    }

    // Método para volver al ViewPager desde el login
    public void hideLogin(){
        Log.d(TAG, "hideLogin() llamado");

        if (isShowingLogin) {
            // Quitar el fragment del stack
            getSupportFragmentManager().popBackStack();

            // Ocultar el contenedor del login
            findViewById(R.id.fragment_container).setVisibility(View.GONE);

            // Mostrar el ViewPager nuevamente
            viewPager.setVisibility(View.VISIBLE);

            isShowingLogin = false;
        }
    }

    @Override
    public void onBackPressed() {
        if (isShowingLogin) {
            // Si estamos mostrando login, volver al ViewPager
            hideLogin();
        } else {
            // Comportamiento normal
            super.onBackPressed();
        }
    }

}