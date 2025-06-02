package com.example.proy_mobile2024;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        Button startButton = findViewById(R.id.button_start);
        Animation glowAnimation = AnimationUtils.loadAnimation(this, R.anim.glow_animation);

        startButton.startAnimation(glowAnimation);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(intent);
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
