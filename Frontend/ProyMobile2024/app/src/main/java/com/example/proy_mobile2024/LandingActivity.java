package com.example.proy_mobile2024;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
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
        Button startButton = findViewById(R.id.button_start);
        Animation glowAnimation = AnimationUtils.loadAnimation(this, R.anim.glow_animation);

        startButton.startAnimation(glowAnimation);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LandingActivity.this, IntroduccionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Verificar si llegó un deeplink (pago de mercadopago)
        Uri data = getIntent().getData();
        if (data != null) {
            this.confirmarPago(data);
         }
    }

    private void confirmarPago(Uri data) {
        String host = data.getHost(); // "pago"
        Log.d("DEEPLINK", "Llegó el deeplink: " + data.toString());
        if (host.equals("pago")) {
            Toast.makeText(this, "¡Pago procesado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LandingActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }
}
