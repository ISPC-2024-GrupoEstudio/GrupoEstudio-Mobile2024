package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.model.Producto;

public class DetalleProductoActivity extends AppCompatActivity {

    private TextView tvNombre, tvDescripcion, tvPrecio;
    private ImageView ivImagen;
    private ImageView btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        // Inicializar vistas
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);
        btnVolver = findViewById(R.id.btnVolver);
        ivImagen = findViewById(R.id.ivImagen);

        btnVolver.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });


        // Obtener datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String descripcion = intent.getStringExtra("descripcion");
            double precio = intent.getDoubleExtra("precio", 0);
            String imagenUrl = intent.getStringExtra("imagen");

            // Mostrar datos en las vistas
            tvNombre.setText(nombre);
            tvDescripcion.setText(descripcion);
            tvPrecio.setText(String.valueOf(precio));
            Glide.with(this)
                    .load(imagenUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivImagen);
        }
    }
}
