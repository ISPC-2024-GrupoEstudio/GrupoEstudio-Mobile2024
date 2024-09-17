package com.example.proy_mobile2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton buttonDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);

        // Maneja la apertura del menú lateral
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        /* Configura el listener para el NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_products) {
                    // Redirige a la actividad GaleriaProducto
                    Intent intent = new Intent(MainActivity.this, GaleriaProductosActivity.class);
                    startActivity(intent);
                }

                // Cierra el drawer después de seleccionar un ítem
                drawerLayout.closeDrawers();
                return true;
            }
        });*/

        // Carga el fragmento de "Sobre Nosotros"
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SobreNosotrosFragment())
                .commit();

        System.out.println(">> MAIN ACTIVITY");//

        //Navegacion con fragments
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                Fragment selectedFragment = null;
                if (id == R.id.nav_profile) {
                    Log.d("MainActivity", "Perfil seleccionado");
                    selectedFragment = new PerfilFragment();
                } else if (id == R.id.nav_login) {
                    selectedFragment = new LoginFragment();
                }else if (id == R.id.nav_contactus) {
                    selectedFragment = new ContactoFragment();
                }else if (id == R.id.nav_registro) {
                    selectedFragment = new RegisterFragment();
                }else if (id == R.id.nav_home){
                    selectedFragment = new SobreNosotrosFragment();
                }
                if (selectedFragment !=null){
                    replaceFragment(selectedFragment);
                }

                if (id == R.id.nav_products) {
                    Intent intent = new Intent(MainActivity.this, GaleriaProductosActivity.class);
                    startActivity(intent);
                }/* if (id == R.id.nav_home) {
                    // Redirige a otra actividad si tienes más
                    Intent intent = new Intent(MainActivity.this, LandingActivity.class);
                    startActivity(intent);
                }*/

                // Cierra el drawer después de seleccionar un ítem
                drawerLayout.closeDrawers();

                return true;
            }

        });
    }

    private void replaceFragment(Fragment fragment){
        Log.d("MainActivity", "Reemplazando el fragmento");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
