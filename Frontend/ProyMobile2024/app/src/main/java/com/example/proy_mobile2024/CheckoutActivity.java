package com.example.proy_mobile2024;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.proy_mobile2024.model.PedidoCheckoutData;
import com.example.proy_mobile2024.model.PedidoCheckoutItemData;
import com.example.proy_mobile2024.model.PreferenciaResponse;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.services.ApiService;
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
    private Button btnPagar;

    private TextView tvTotalConDescuento;
    private List<Cupon> cuponesAplicables = new ArrayList<>();
    private double totalSinDescuento = 0.0;
    private double totalConDescuento = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerCheckout = findViewById(R.id.recyclerProductos);
        recyclerCheckout.setLayoutManager(new LinearLayoutManager(this));

        listaCarrito = new ArrayList<>();
        tvTotal = findViewById(R.id.tvTotal);
        tvTotalConDescuento = findViewById(R.id.tvTotalConDescuento);

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

//        for (Carrito item : listaCarrito) {
//            double precio = item.getProducto().getPrecio(); // o getPrecioUnitario()
//            int cantidad = item.getCantidad();
//            total += precio * cantidad;
//        }
//
//        tvTotal.setText(String.format("Total: $%.2f", total));
        for (Carrito item : listaCarrito) {
            double precio = item.getProducto().getPrecio();
            int cantidad = item.getCantidad();
            totalSinDescuento += precio * cantidad;
        }

        tvTotal.setText(String.format("Total: $%.2f", totalSinDescuento));
        obtenerYAplicarCupones();


        btnVolver = findViewById(R.id.btnVolverCart);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la actividad de productos
                Intent intent = new Intent(CheckoutActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

        btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });

    }

    private void obtenerYAplicarCupones() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String token = preferences.getString("access_token", "");
        String username = preferences.getString("username", "");

        ApiService apiService = RetrofitClient.getInstance(getApplicationContext()).getApiService();

        apiService.obtenerCuponesUsuario("Bearer " + token, username)
                .enqueue(new Callback<List<Cupon>>() {
                    @Override
                    public void onResponse(Call<List<Cupon>> call, Response<List<Cupon>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            cuponesAplicables = response.body();
                            aplicarDescuentos();
                        } else {
                            Log.e("Checkout", "No se pudieron obtener cupones");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cupon>> call, Throwable t) {
                        Log.e("Checkout", "Error al obtener cupones: " + t.getMessage());
                    }
                });
    }

    private void aplicarDescuentos() {
        double total = totalSinDescuento;

        double montoDescuentoFijo = 0.0;
        double porcentajeTotal = 0.0;

        for (Cupon cupon : cuponesAplicables) {
            if (cupon.getTipoDescuento().equalsIgnoreCase("monto")) {
                montoDescuentoFijo += cupon.getValorDescuento();
            } else if (cupon.getTipoDescuento().equalsIgnoreCase("porcentaje")) {
                porcentajeTotal += cupon.getValorDescuento(); // acumulativo
            }
        }

        // Primero aplicamos el porcentaje
        total -= (total * (porcentajeTotal / 100.0));

        // Luego restamos el monto fijo
        total -= montoDescuentoFijo;

        if (total < 0) total = 0;

        totalConDescuento = total;

        tvTotalConDescuento.setText(String.format("Total con descuentos: $%.2f", totalConDescuento));
    }


    private void checkout() {
        aplicarDescuentos();
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String nombre_usuario = sharedPreferences.getString("id_usuario", "");

        Log.d("Checkout", "Nombre de usuario: " + nombre_usuario);
        Log.d("Checkout", "Monto final con descuento: " + totalConDescuento);

        PedidoCheckoutData pedidoCheckoutData = new PedidoCheckoutData();
        pedidoCheckoutData.setExternal_reference(nombre_usuario);
        pedidoCheckoutData.setItemsCarrito(listaCarrito);
        pedidoCheckoutData.setMontoFinal(totalConDescuento);

        RetrofitClient.getInstance(this).getApiService().obtenerPreferencia(pedidoCheckoutData).enqueue(new Callback<PreferenciaResponse>() {
            @Override
            public void onResponse(Call<PreferenciaResponse> call, Response<PreferenciaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                  Intent intent = new Intent(Intent.ACTION_VIEW);
                  intent.setData(Uri.parse(response.body().getInit_point()));
                  startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PreferenciaResponse> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error al cargar el carrito", Toast.LENGTH_SHORT).show();
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