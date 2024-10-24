package com.example.proy_mobile2024;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.adapter.ProductoAdapter;
import com.example.proy_mobile2024.model.ItemCarritoData;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.model.Usuario;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaleriaProductosActivity extends AppCompatActivity {

    private Context context;
    private ProductoAdapter productoAdapter;
    private List <Producto> productosList;
    private List <Producto> filteredProductList;
    private RecyclerView recyclerView;
    private ProgressBar progressBarProductos;
    private TabLayout tblCategorias;
    private ImageView btnVolverGaleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_galeria_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        View botonCarrito = findViewById(R.id.botonCarrito);

        // Configuramos el click listener para el botón del carrito
        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la actividad del carrito
                Intent intent = new Intent(GaleriaProductosActivity.this, CarritoActivity.class);
                startActivity(intent);  // Iniciar la actividad del carrito
            }
        });
    }

    public void init(){
        context = GaleriaProductosActivity.this;
        recyclerView = findViewById(R.id.recyclerViewProductos);
        progressBarProductos = findViewById(R.id.progressBarProductos);
        tblCategorias = findViewById(R.id.tblCategorias);
        btnVolverGaleria = findViewById(R.id.btnVolver);

        btnVolverGaleria.setOnClickListener(v -> {
            Intent intent = new Intent(GaleriaProductosActivity.this, MainActivity.class);
            startActivity(intent);
        });


        tblCategorias.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterProductList(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });



        getProductosList();


        // Crear el adaptador
        productoAdapter = new ProductoAdapter(context, productosList);

        // Asignar el adaptador al RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columnas
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productoAdapter);
        tblCategorias.selectTab(tblCategorias.getTabAt(0));
        filterProductList("Accesorios");


    }



    private void getProductosList() {
        productosList = new ArrayList<Producto>();
        progressBarProductos.setVisibility(ProgressBar.VISIBLE);

        RetrofitClient.getInstance(this).getApiService().obtenerProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Producto> listaProductos = response.body();
                    productoAdapter.setProductosList(listaProductos);
                    progressBarProductos.setVisibility(ProgressBar.INVISIBLE);
                } else {
                    System.out.println("Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });


    }

    private void filterProductList(String categoria) {
        int categoriaId = 0;
        switch (categoria) {
            case "Accesorios": categoriaId = 1; break;
            case "Cuchas": categoriaId = 2; break;
            case "Juguetes": categoriaId = 3; break;
            case "Ropa": categoriaId = 4; break;
        }


        List<Producto> filteredList = new ArrayList<>();
        for (Producto producto : productosList) {
            if (producto.getCategoria() == categoriaId) {
                filteredList.add(producto);
            }
        }
        filteredProductList = filteredList;
        productoAdapter.setProductosList(filteredProductList);
    }


}