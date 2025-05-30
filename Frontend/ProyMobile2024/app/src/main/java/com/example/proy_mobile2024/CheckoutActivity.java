package com.example.proy_mobile2024;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.adapter.CarritoAdapter;
import com.example.proy_mobile2024.model.Carrito;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCTOS = "productos_carrito";

    private RecyclerView recyclerCheckout;
    private CarritoAdapter carritoAdapter;
    private List<Carrito> listaCarrito;

    private TextView tvTotal;
    private RecyclerView recyclerView;
    private ArrayList<Producto> productos;
    private ImageButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout); // Asegurate de tener este layout

        recyclerCheckout = findViewById(R.id.recyclerProductos); // Usá el mismo ID que en el fragmento
        recyclerCheckout.setLayoutManager(new LinearLayoutManager(this));

        listaCarrito = new ArrayList<>();
        tvTotal = findViewById(R.id.tvTotal);
        double total = 0;



        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCTOS)) {
            productos = (ArrayList<Producto>) intent.getSerializableExtra(EXTRA_PRODUCTOS);

            if(productos != null){
                Log.d("Checkout", "Productos recibidos desde Intent: " + productos.size());
            } else {
                Log.d("Checkout", "productos es null");
            }

            for (Producto producto : productos) {
                Log.d("Checkout", "Producto: " + producto.getNombre()); // o cualquier atributo para verificar
                Carrito item = new Carrito();
                item.setProducto(producto);
                item.setCantidad(1); // O la cantidad real
                listaCarrito.add(item);
            }

            Log.d("Checkout", "listaCarrito después de agregar productos: " + listaCarrito.size());


            carritoAdapter = new CarritoAdapter(this, listaCarrito, true);
            recyclerCheckout.setAdapter(carritoAdapter);
        } else {
            // Si no se pasan productos, cargamos desde backend
            carritoAdapter = new CarritoAdapter(this, listaCarrito, true);
            recyclerCheckout.setAdapter(carritoAdapter);
            cargarCarrito();
        }

        for (Carrito item : listaCarrito) {
            double precio = item.getProducto().getPrecio(); // o getPrecioUnitario()
            int cantidad = item.getCantidad();
            total += precio * cantidad;
        }

        tvTotal.setText(String.format("Total: $%.2f", total));

        btnVolver = findViewById(R.id.btnVolverCart);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la actividad de productos
                Intent intent = new Intent(CheckoutActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void cargarCarrito() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        RetrofitClient.getInstance(this).getApiService().obtenerCarrito(username).enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaCarrito = response.body();
                    carritoAdapter.setCarritoList(listaCarrito);
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error al cargar el carrito", Toast.LENGTH_SHORT).show();
            }
        });
    }
}