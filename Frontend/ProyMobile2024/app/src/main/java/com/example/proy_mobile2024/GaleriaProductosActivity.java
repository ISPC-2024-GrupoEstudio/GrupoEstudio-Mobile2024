package com.example.proy_mobile2024;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.adapter.ProductoAdapter;
import com.example.proy_mobile2024.model.Producto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

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
    }

    public void init(){
        context = GaleriaProductosActivity.this;
        recyclerView = findViewById(R.id.recyclerViewProductos);
        progressBarProductos = findViewById(R.id.progressBarProductos);
        tblCategorias = findViewById(R.id.tblCategorias);
        btnVolverGaleria = findViewById(R.id.btnVolverGaleria);

        btnVolverGaleria.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
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
        progressBarProductos.setVisibility(ProgressBar.VISIBLE);
        // Obtener la lista de productos
        productosList = new ArrayList<Producto>();

        productosList.add(new Producto(2, "Cucha Perro", "Cucha para perro madera", 45000.0, "https://i.ibb.co/VWr3jPj/Cucha-Exterior.png", 2));
        productosList.add(new Producto(3, "Buzo Adidog", "Buzo algodon con friza elastizado", 8000.0, "https://i.ibb.co/QmvL3pK/Buzo-Miami.png", 4));
        productosList.add(new Producto(6, "Capa", "Para la lluvia con capucha", 8000.0, "https://i.ibb.co/hDsc0Hz/Buzo-Puffer.png", 4));
        productosList.add(new Producto(7, "Huesito", "Hueso de fantasía", 1200.0, "https://i.ibb.co/bJn9zFr/Huesos.png", 3));
        productosList.add(new Producto(8, "Ponchito", "Poncho de lana con detalles en morley", 15600.0, "https://i.ibb.co/xg9KJD1/Sweter-Gato.png", 2));
        productosList.add(new Producto(9, "Cucha calabaza", "Cucha estilo calabaza para gatos, color naranja, con relleno de guata y una abertura principal.", 3500.0, "https://i.ibb.co/5vVSGxk/Calabaza.png", 2));
        productosList.add(new Producto(10, "Cucha Madera", "Cucha de madera para perros ideal para exteriores, con techo corredizo, comedero y bebedero de agua. Detalles en negro en sus bordes", 3500.0, "https://i.ibb.co/Yj42b5p/Cucha-Exterior.png", 2));
        productosList.add(new Producto(11, "Cucha Cat", "Cama para gatos con forma de gato, cubierta con peluche e interiór de felpa. Incluye un pompón colgante como juguete.", 3500.0, "https://i.ibb.co/TKPQhjq/Forma-Gato.png", 2));
        productosList.add(new Producto(12, "Cucha Sillon", "Cama estilo sillon para perros con relleno de guata, abierto, coor gris con detalles blancos en sus bordes.", 3000.0, "https://i.ibb.co/FWr40Sw/Sillon.png", 2));
        productosList.add(new Producto(13, "Cama colgante", "Cama colgante para gatos con cubierta azul oscuro con textura, abertura para su entrada e interior acolchado", 3800.0, "https://i.ibb.co/s6TQ6SM/Gato-Colgante.png", 2));
        productosList.add(new Producto(14, "Cama donna", "Cama tipo Donna con relleno de guata y cubierta de felpa para perros en varios colores", 4100.0, "https://i.ibb.co/tLkMRj2/Donnas.png", 2));
        productosList.add(new Producto(15, "Collar para Gato", "Collar para gato con broche de seguridad y cascabel en varios colores.", 3000.0, "https://i.ibb.co/g3Fgqww/Collar-Gato.png", 2));
        productosList.add(new Producto(16, "Collar para Gato", "Collar para gato con broche de seguridad y cascabel en varios colores.", 3000.0, "https://i.ibb.co/g3Fgqww/Collar-Gato.png", 1));
        productosList.add(new Producto(17, "Bolso transportador", "Bolso trasportador para mascotas con interior de felpa, red para una mejor oxigenación y manijas de agarre seguro.", 60000.0, "https://i.ibb.co/61VqkHH/Transportador.png", 1));
        productosList.add(new Producto(18, "Collar para perro", "Collares para perros con broche de seguridad y con estampados diversos.", 10000.0, "https://i.ibb.co/jvZxXgg/Collar-Perro.png", 1));
        productosList.add(new Producto(19, "Rascador para gato", "Rascador para gato de 4 pisos con bolsa colgante, cucha con salidas, y juguete en su piso superior. Todos los pisos estan cubiertos de felpa con detalles en gris.", 100000.0, "https://i.ibb.co/5jYk6K5/Rascador.png", 1));
        productosList.add(new Producto(20, "Correa extensible", "Correa extensible para mascotas con gancho para enganchar en el collar y un largo total de 1 metro con botón regulable.", 20000.0, "https://i.ibb.co/Rp00xwC/Correa.png", 1));
        productosList.add(new Producto(21, "Comedero + Dispenser", "Base blanca con comedero transparente en forma de gato y dispensador de agua.", 25000.0, "https://i.ibb.co/hMxnRKW/Vertedero.png", 1));
        productosList.add(new Producto(22, "Caña con pluma", "Juguete de tipo caña con una pluma en su extremo para jugar con gatos.", 3000.0, "https://i.ibb.co/m987M63/Jueguete-Gato.png", 3));
        productosList.add(new Producto(23, "Hueso para perro", "Huesos de cuero comestible para perros", 1500.0, "https://i.ibb.co/mG1rYpS/Huesos.png", 3));
        productosList.add(new Producto(24, "Peluche Felpa", "Peluche de felpa tipo patita para gatos. Varios colores.", 2500.0, "https://i.ibb.co/wSTP5j6/Peluche-Gato.png", 3));
        productosList.add(new Producto(25, "Sogas trenzadas", "Sogas trenzadas para jugar con perros. Varios colores.", 2500.0, "https://i.ibb.co/CMy9xZ8/Sogas.png", 3));
        productosList.add(new Producto(26, "Raton de Juguete", "Ratón de juguete con control remoto para divertirte con tu gato.", 4500.0, "https://i.ibb.co/fYNH3sv/Raton.png", 3));
        productosList.add(new Producto(27, "Lanzador de pelota", "Lanzador automático de pelotas para perros. Incluuye 6 pelotas de regalo.", 5500.0, "https://i.ibb.co/QDR8RzX/Arrojador-Automatico.png", 3));
        productosList.add(new Producto(28, "Chaleco Puffer", "Chaleco estilo puffer para perro mediano, color verde militar con interior de corderito.", 13000.0, "https://i.ibb.co/sVDqPDc/Buzo-Puffer.png", 4));
        productosList.add(new Producto(29, "Buzo Adidog", "Buzo con capucha para perros pequeños, color gris con estampado.", 8000.0, "https://i.ibb.co/XbzxG9B/Buzo-Adidog.png", 4));


        progressBarProductos.setVisibility(ProgressBar.INVISIBLE);

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