package com.example.proy_mobile2024;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.model.ItemCarritoData;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleProductoActivity extends AppCompatActivity {
    private Context context;
    private TextView tvNombre, tvDescripcion, tvPrecio;
    private ImageView ivImagen;
    private ImageView btnVolver;
    private Button botonAgregar;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        // Inicializar vistas
        context = DetalleProductoActivity.this;
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);
        btnVolver = findViewById(R.id.btnVolver);
        ivImagen = findViewById(R.id.ivImagen);
        botonAgregar = findViewById(R.id.botonAgregar);

        btnVolver.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        botonAgregar.setOnClickListener(v -> {
            if (!usuarioEstaConectado()) {
                // 1. Mostrar el mensaje Toast
                Toast.makeText(context, "Debes estar conectado para poder agregar un producto al carrito", Toast.LENGTH_LONG).show();

                new AlertDialog.Builder(DetalleProductoActivity.this)
                        .setTitle("Atención")
                        .setMessage("Debés iniciar sesión para agregar productos al carrito.")
                        .setPositiveButton("Aceptar", null)
                        .show();

            } else {
                agregarProductoAlCarrito(producto);
            }
        });


        // Obtener datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String descripcion = intent.getStringExtra("descripcion");
            double precio = intent.getDoubleExtra("precio", 0);
            String imagenUrl = intent.getStringExtra("imagen");
            int idProducto = intent.getIntExtra("id_producto", 0);

            this.obtenerProducto(idProducto);

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

    private void agregarProductoAlCarrito(Producto producto) {
        ItemCarritoData item = new ItemCarritoData();
        item.setId_producto(producto.getId_producto());
        item.setCantidad(1);
        item.setNombre_usuario(obtenerUsuarioConectado());

        Call<Void> call = RetrofitClient.getInstance(context).getApiService().agregarProductoACarrito(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Producto agregado con exito al carrito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error al agregar el producto, intente nuevamente", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String obtenerUsuarioConectado() {
        SharedPreferences preferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getString("username", null);
    }

    private boolean usuarioEstaConectado() {
        SharedPreferences preferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    private void obtenerProducto(int id) {
        Call<Producto> call = RetrofitClient.getInstance(context).getApiService().obtenerProductosPorId(id);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful()) {
                    producto = response.body();
                } else {
                    Toast.makeText(context, "Error al obtener el producto", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Toast.makeText(context, "Error al obtener el producto", Toast.LENGTH_LONG).show();
            }
        });
    }
}
