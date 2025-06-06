package com.example.proy_mobile2024;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DashboardActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    CarruselAdapter adapter;

    CardView cardHistorial;
    CardView cardCupones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            // Para íconos oscuros en status bar transparente
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                );
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        init();
        setupCarrusel();
    }

    //Inicializacion y manejo del boton volver atras.
    public void init(){
        ImageView btnVolverDashboard = findViewById(R.id.btnVolverDashboard);

        cardHistorial = findViewById(R.id.cardHistorial);
        cardCupones = findViewById(R.id.cardCupones);

        cardHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, HistorialComprasActivity.class);
            startActivity(intent);
        });

        cardCupones.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, CuponesActivity.class);
            startActivity(intent);
        });

        btnVolverDashboard.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // Configuración del carrusel
    private void setupCarrusel() {
        viewPager = findViewById(R.id.viewPager);

        List<CarruselDashboard> items = new ArrayList<>();
        items.add(new CarruselDashboard("Peluches", R.drawable.item_carrusel1));
        items.add(new CarruselDashboard("Cuchas", R.drawable.item_carrusel2));
        items.add(new CarruselDashboard("Ropa", R.drawable.item_carrusel3));

        CarruselAdapter adapter = new CarruselAdapter(items);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        // Permitir ver los ítems vecinos
        RecyclerView recyclerView = (RecyclerView) viewPager.getChildAt(0);
        recyclerView.setClipToPadding(false);
        recyclerView.setClipChildren(false);

        // Efecto tipo carrusel/cascada
        int offsetPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()
        );

        viewPager.setPageTransformer((page, position) -> {
            float scale = 0.85f + (1 - Math.abs(position)) * 0.15f;
            page.setScaleY(scale);
            page.setScaleX(scale);
            page.setTranslationX(-position * offsetPx);
        });
    }

}