package com.example.proy_mobile2024;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
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



        if (savedInstanceState == null) {
            // Carga el fragmento de "Sobre Nosotros" solo si no hay un estado guardado
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SobreNosotrosFragment())
                    .commit();
        } else {
            // Restaura el estado del fragmento desde savedInstanceState
            Fragment fragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        }

        System.out.println(">> MAIN ACTIVITY");//

        //Navegacion con fragments
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                for (int i = 0; i < navigationView.getMenu().size(); i++) {
                    navigationView.getMenu().getItem(i).setChecked(false);
                }
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                Fragment selectedFragment = null;

                if (id == R.id.nav_logout) {
                    // Llama al método logout
                    logout();
                    return true;
                }if (id == R.id.nav_login) {
                    selectedFragment = new LoginFragment();
                }else if (id == R.id.nav_contactus) {
                    selectedFragment = new ContactoFragment();
                }else if (id == R.id.nav_registro) {
                    selectedFragment = new RegisterFragment();
                }else if (id == R.id.nav_home){
                    selectedFragment = new SobreNosotrosFragment();
                }
                else if (id == R.id.nav_cart){
                selectedFragment = new CarritoFragment();
                }
                if (selectedFragment !=null){
                    replaceFragment(selectedFragment);
                }

                if (id == R.id.nav_products) {
                    Intent intent = new Intent(MainActivity.this, GaleriaProductosActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.nav_profile) {
                    Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
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

    private void logout() {
       try {
        // Limpia las credenciales del usuario
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Elimina las credenciales
        editor.apply();

        // Redirige al fragmento de inicio de sesión
        Fragment loginFragment = new LoginFragment();
        replaceFragment(loginFragment);

        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();
    }catch (Exception e) {
           // Manejo de errores
           Toast.makeText(this, "Error al cerrar sesión: " + e.getMessage(), Toast.LENGTH_LONG).show();
       }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guarda el fragmento actual en el estado
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment != null) {
            getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
        }
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
