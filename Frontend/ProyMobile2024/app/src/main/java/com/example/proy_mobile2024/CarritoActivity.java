package com.example.proy_mobile2024;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proy_mobile2024.adapter.CarritoAdapter;
import com.example.proy_mobile2024.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarritoAdapter carritoAdapter;
    private Button btnVaciarCarrito;
    private ImageButton btnVolver;
    private ImageButton btnVolverMain;
    private List<Producto> productosCarrito;

    public static void agregarProducto(Producto producto) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            Log.d("CarritoActivity", "El usuario no está autenticado. Redirigiendo a inicio de sesión.");
            Toast.makeText(this, "Debes iniciar sesión para acceder al carrito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CarritoActivity.this, LandingActivity.class);
            startActivity(intent);
            finish(); // Termina esta actividad
            return; // Asegúrate de salir del método si el usuario no está autenticado
        }

        // Si el token existe y el usuario está registrado, continuar con la configuración de la actividad
        recyclerView = findViewById(R.id.recyclerViewCarrito);



        // Obtener los productos del carrito
        List<Producto> productosCarrito = Carrito.obtenerProductos();

        // Configurar el adaptador del carrito
        CarritoAdapter carritoAdapter = new CarritoAdapter(this, productosCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carritoAdapter);

        // Botón para vaciar el carrito
        btnVaciarCarrito = findViewById(R.id.btnVaciarCarrito);
        btnVaciarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vaciar la lista de productos
                productosCarrito.clear();
                // Notificar al adaptador que el dataset cambió
                carritoAdapter.notifyDataSetChanged();
            }
        });
        // Configurar el botón para volver a la actividad de productos
        btnVolver = findViewById(R.id.btnVolverCart);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la actividad de productos
                Intent intent = new Intent(CarritoActivity.this, GaleriaProductosActivity.class);
                startActivity(intent);

            }
        });

        // Configurar el ImageButton para volver a la MainActivity
        btnVolverMain = findViewById(R.id.btnVolverMain); // Aquí seguimos buscando el botón
        btnVolverMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la MainActivity
                Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: cerrar la actividad actual
            }
        });






    }



}


