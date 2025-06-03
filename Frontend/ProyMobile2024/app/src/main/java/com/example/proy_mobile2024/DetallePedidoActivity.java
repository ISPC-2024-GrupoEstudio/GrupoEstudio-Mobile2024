package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.proy_mobile2024.adapter.ProductoPedidoAdapter;
import com.example.proy_mobile2024.model.ProductosXPedido;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePedidoActivity extends AppCompatActivity {
    private RecyclerView recyclerProductos;
    private TextView tvTotal;
    private ProductoPedidoAdapter adapter;

    private int idPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_pedido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerProductos = findViewById(R.id.recyclerProductos);
        tvTotal = findViewById(R.id.tvTotalPedido);

        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divisor_detalle));
        recyclerProductos.addItemDecoration(divider);
        adapter = new ProductoPedidoAdapter();
        recyclerProductos.setAdapter(adapter);

        idPedido = getIntent().getIntExtra("id_pedido", -1);

        if (idPedido != -1){
            obtenerProductosDelPedido(idPedido);
        }else{
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

                    double total = 0;
                    for (ProductosXPedido item : productos){
                        double subtotal = item.getCantidad() * item.getId_producto().getPrecio();
                        total += subtotal;
                    }

                    tvTotal.setText("Total: $" + String.format("%.2f", total));
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
}