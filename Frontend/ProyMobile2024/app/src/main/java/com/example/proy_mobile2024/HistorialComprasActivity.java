package com.example.proy_mobile2024;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.adapter.PedidoAdapter;
import com.example.proy_mobile2024.model.Pedido;
import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialComprasActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AuthPrefs";
    RecyclerView recyclerView;
    PedidoAdapter adapter;
    List<Pedido> listaFiltrada = new ArrayList<>();
    String username;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_compras);

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

        recyclerView = findViewById(R.id.recyclerHistorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);


        // Intentar obtener el username de diferentes claves posibles
        username = prefs.getString("id_usuario", null);
        if (username == null) {
            username = prefs.getString("username", null);
        }
        if (username == null) {
            username = prefs.getString("nombre", null);
        }

        accessToken = prefs.getString("accessToken", null);

        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);


        if (isLoggedIn && username != null){
            cargarPedidos();
        }else {
            Toast.makeText(this, "NO SE ENCONTRO EL USUARIO", Toast.LENGTH_SHORT).show();
        }

        init();
    }

    private void cargarPedidos(){
        ApiService apiService = RetrofitClient.getInstance(this).getApiService();
        Call<List<Pedido>> call = apiService.getPedidos();


        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Pedido> pedidos = response.body();

                    //Filtrar pedidos por usuario
                    listaFiltrada.clear();
                    for (Pedido pedido:pedidos){
                        //Verificar si el pedido pertenece al usuario loggeado
                        if (pedido.getNombreUsuario() != null && pedido.getNombreUsuario().equals(username)){
                            listaFiltrada.add(pedido);
                        }
                    }

                    adapter = new PedidoAdapter(listaFiltrada);
                    recyclerView.setAdapter(adapter);

                    if (listaFiltrada.isEmpty()){
                        Toast.makeText(HistorialComprasActivity.this, "No hay pedidos realizados.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.e("HISTORIALERROR", "Error en la rta: " + response.code());
                    Toast.makeText(HistorialComprasActivity.this, "Error al cargar el historial", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable throwable) {
                Log.e("HistorialError", "ERROR DE CONEXION; " + throwable.getMessage());
                Toast.makeText(HistorialComprasActivity.this, "Error de conexión: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Inicializacion y manejo del boton volver atras.
    public void init(){
        ImageView btnVolverHistorial = findViewById(R.id.btnVolverHistorial);

        btnVolverHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialComprasActivity.this, DashboardActivity.class);

            startActivity(intent);
            finish(); 
        });
    }
}