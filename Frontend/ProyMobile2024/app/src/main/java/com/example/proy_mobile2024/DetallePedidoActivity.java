package com.example.proy_mobile2024;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.proy_mobile2024.adapter.ProductoPedidoAdapter;
import com.example.proy_mobile2024.model.ProductosXPedido;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import org.json.JSONException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;


public class DetallePedidoActivity extends AppCompatActivity {
    private RecyclerView recyclerProductos;
    private ProductoPedidoAdapter adapter;
    TextView tvTotalPedido, tvDireccionEnvio, tvCodigoPostal, tvCiudad, tvCostoEnvio, tvDescuento;

    private int idPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        tvTotalPedido = findViewById(R.id.tvTotalPedido);
        tvDireccionEnvio = findViewById(R.id.tvDireccionEnvio);
        tvCodigoPostal = findViewById(R.id.tvCodigoPostal);
        tvCiudad = findViewById(R.id.tvCiudad);
        tvCostoEnvio = findViewById(R.id.tvCostoEnvio);
        tvDescuento = findViewById(R.id.tvDescuento);

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

        int idPedido = getIntent().getIntExtra("id_pedido", -1);
        if (idPedido != -1) {
            obtenerDatosDelPedido(idPedido);  // <- NUEVO
        }

        recyclerProductos = findViewById(R.id.recyclerProductos);
        tvTotalPedido = findViewById(R.id.tvTotalPedido);

        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divisor_detalle));
        recyclerProductos.addItemDecoration(divider);
        adapter = new ProductoPedidoAdapter();
        recyclerProductos.setAdapter(adapter);

        idPedido = getIntent().getIntExtra("id_pedido", -1);

        idPedido = getIntent().getIntExtra("id_pedido", -1);
        if (idPedido != -1) {
            obtenerDatosDelPedido(idPedido);
            obtenerProductosDelPedido(idPedido);
        } else {
            Toast.makeText(this, "ID PEDIDO INVALIDO", Toast.LENGTH_SHORT).show();
        }


        init();
    }

    private void obtenerProductosDelPedido(int idPedido){
        ApiService apiService = RetrofitClient.getInstance(this).getApiService();

        Call<List<ProductosXPedido>> call = apiService.obtenerProductosPorPedido(idPedido);
        call.enqueue(new Callback<List<ProductosXPedido>>() {
            @Override
            public void onResponse(Call<List<ProductosXPedido>> call, Response<List<ProductosXPedido>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<ProductosXPedido> productos = response.body();
                    adapter.setProductoXPedidoList(productos);

                }else{
                    Toast.makeText(DetallePedidoActivity.this, "No se pudieron obtener los productos", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<ProductosXPedido>> call, Throwable t) {
                Toast.makeText(DetallePedidoActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    //Inicializacion y manejo del boton volver atras.
    public void init(){
        ImageView btnVolverDetalle = findViewById(R.id.btnVolverDetalle);

        btnVolverDetalle.setOnClickListener(v -> {
            Intent intent = new Intent(DetallePedidoActivity.this, HistorialComprasActivity.class);

            startActivity(intent);
            finish();
        });
    }

    private void obtenerDatosDelPedido(int idPedido) {
        String url = "https://98f1-181-111-12-240.ngrok-free.app/api/pedidos/" + idPedido + "/"; // Cambiá TU_BACKEND_URL

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        double total = response.has("total") ? response.getDouble("total") : 0.0;
                        double envio = response.has("costo_envio") ? response.getDouble("costo_envio") : 0.0;
                        String direccion = response.has("domicilio_envio") ? response.getString("domicilio_envio") : "-";
                        String cp = response.has("codigo_postal") ? response.getString("codigo_postal") : "-";
                        String ciudad = response.has("ciudad_envio") ? response.getString("ciudad_envio") : "-";
                        double descuento = response.has("descuento") ? response.getDouble("descuento") : 0.0;

                        tvTotalPedido.setText("Total: $" + total);
                        tvDireccionEnvio.setText("Dirección: " + direccion);
                        tvCodigoPostal.setText("Código Postal: " + cp);
                        tvCiudad.setText("Ciudad: " + ciudad);
                        tvCostoEnvio.setText("Costo envío: $" + envio);
                        tvDescuento.setText("Descuento: $" + descuento);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API", "Error al obtener pedido: " + error.toString());
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }

}