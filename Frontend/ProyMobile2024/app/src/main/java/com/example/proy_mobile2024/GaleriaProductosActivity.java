package com.example.proy_mobile2024;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.proy_mobile2024.model.CategoriaProducto;
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
    private List<CategoriaProducto> categorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_galeria_productos);
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

        init();

        View botonCarrito = findViewById(R.id.botonCarrito);

        // Configuramos el click listener para el botón del carrito
        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuarioEstaConectado()) {
                    // Mostrar Toast
                    Toast.makeText(context, "Debes estar conectado para poder agregar un producto al carrito", Toast.LENGTH_LONG).show();

                    new AlertDialog.Builder(GaleriaProductosActivity.this)
                            .setTitle("Atención")
                            .setMessage("Debés iniciar sesión para agregar productos al carrito.")
                            .setPositiveButton("Aceptar", null)
                            .show();

                } else {
                        // Crear un Intent para ir a la actividad del carrito
                        Intent intent = new Intent(GaleriaProductosActivity.this, CarritoActivity.class);
                        startActivity(intent);  // Iniciar la actividad del carrito
                    }
            }
        });
    }

    public void init(){
        context = GaleriaProductosActivity.this;
        productosList = new ArrayList<Producto>();
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
                CategoriaProducto categoria = (CategoriaProducto) tab.getTag();
                filterProductList(categoria);
                System.out.println(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


        cargarCategorias();


        // Crear el adaptador
        productoAdapter = new ProductoAdapter(context, productosList);

        // Asignar el adaptador al RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columnas
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productoAdapter);
        tblCategorias.selectTab(tblCategorias.getTabAt(0));


    }



    private void getProductosList() {
        progressBarProductos.setVisibility(ProgressBar.VISIBLE);

        RetrofitClient.getInstance(this).getApiService().obtenerProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Producto> listaProductos = response.body();
                    productoAdapter.setProductosList(listaProductos);
                    progressBarProductos.setVisibility(ProgressBar.INVISIBLE);
                    productosList = listaProductos;
                    if (categorias.size() > 0) {
                        filterProductList(categorias.get(0));
                    }
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

    private void filterProductList(CategoriaProducto categoria) {
        List<Producto> filteredList = new ArrayList<>();
        System.out.println("Product list len" + productosList.size());
        for (Producto producto : productosList) {
            System.out.println("cat");
            System.out.println(producto.getCategoria());
            if (producto.getCategoria() == categoria.getId_categoria_producto()) {
                filteredList.add(producto);
            }
        }
        filteredProductList = filteredList;
        productoAdapter.setProductosList(filteredProductList);
    }

    private boolean usuarioEstaConectado() {
        SharedPreferences preferences = this.context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    private void cargarCategorias() {
        Call<List<CategoriaProducto>> call = RetrofitClient.getInstance(context).getApiService().obtenerCategorias();
        call.enqueue(new Callback<List<CategoriaProducto>>() {
            @Override
            public void onResponse(Call<List<CategoriaProducto>> call, Response<List<CategoriaProducto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categorias = response.body();
                    tblCategorias.removeAllTabs();
                    for (int i = 0; i < categorias.size(); i++) {
                        TabLayout.Tab tab = tblCategorias.newTab();
                        tab.setText(categorias.get(i).getNombre());
                        tab.setTag(categorias.get(i));
                        tblCategorias.addTab(tab);
                    }
                    getProductosList();
                } else {
                    System.out.println("Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaProducto>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

}