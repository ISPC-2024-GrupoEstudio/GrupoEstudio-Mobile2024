package com.example.proy_mobile2024;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
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


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.proy_mobile2024.model.Producto;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import android.content.Intent;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton buttonDrawerToggle;
    TextView navHeaderTitle;
    private ImageView headerProfileImage;


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

        // Después de obtener el headerView
        View headerView = navigationView.getHeaderView(0);
        headerProfileImage = headerView.findViewById(R.id.nav_header_image); // Asegúrate de añadir este ID en tu nav_header.xml

        // Cargar la imagen de perfil
        loadProfileImage();

        // Llama a checkLoginStatus() para establecer el estado inicial de los ítems del menú
        checkLoginStatus();

        // Verificar si el login fue exitoso desde IntroduccionActivity
        if (getIntent().getBooleanExtra("loginSuccess", false)) {
            // Actualizar el NavigationView con los datos del usuario
            updateNavigationHeaderFromPreferences();

            // Opcional: navegar directamente al fragmento deseado
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SobreNosotrosFragment())
                        .commit();
            }

            // Mostrar mensaje de bienvenida
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
        }
        // Inicializa el NavigationView
        navigationView = findViewById(R.id.nav_view);


        // Obtén la vista del encabezado
        navHeaderTitle = headerView.findViewById(R.id.nav_header_title); // Inicializa aquí


        // Referencia al TextView del encabezado
        TextView navHeaderTitle = headerView.findViewById(R.id.nav_header_title); // Cambia esto según tu XML

        // Obtén el username desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "Usuario Desconocido"); // Cambia "username" por la clave que usas


        // Establece el nombre en el TextView del encabezado
        navHeaderTitle.setText(username);
        //if (!apellido.isEmpty()) {
        //    navHeaderTitle.setText(String.format("%s %s", nombre, apellido)); // Combina nombre y apellido si lo deseas
        //}

        // Maneja la apertura del menú lateral
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        // Verifica el estado de inicio de sesión al cargar la actividad
        //checkLoginStatus();  // Verifica si el usuario está logueado o no


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
                if (id == R.id.nav_login) {
                    selectedFragment = new LoginFragment();
                }else if (id == R.id.nav_contactus) {
                    selectedFragment = new ContactoFragment();
                }else if (id == R.id.nav_registro) {
                    selectedFragment = new RegisterFragment();
                }else if (id == R.id.nav_home){
                    selectedFragment = new SobreNosotrosFragment();
                } else if (id == R.id.nav_logout) { // Manejo del logout
                logoutClick();
                }else if (id == R.id.nav_web) {
                String url = "https://github.com/ISPC-2024-GrupoEstudio/GrupoEstudio-2024";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true; // No es necesario continuar con el procesamiento
            }

                if (selectedFragment !=null){
                    replaceFragment(selectedFragment);
                }

                if (id == R.id.nav_products) {
                    Intent intent = new Intent(MainActivity.this, GaleriaProductosActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.nav_cart) {
                    Intent intent = new Intent(MainActivity.this, CarritoActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.nav_profile) {
                    Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.nav_dashboard) {
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                /* if (id == R.id.nav_home) {
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

    // Reemplazar el método loadProfileImage existente en MainActivity.java con esta versión:
    public void loadProfileImage() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String profileImageUrl = preferences.getString("profile_image_url", "");

        if (headerProfileImage != null) {
            if (!profileImageUrl.isEmpty()) {
                Glide.with(this)
                        .load(profileImageUrl)
                        .transform(new CircleCrop())
                        .placeholder(R.drawable.foto_icon)
                        .error(R.drawable.foto_icon)
                        .into(headerProfileImage);
                Log.d("MainActivity", "Imagen de perfil cargada desde URL: " + profileImageUrl);
            } else {
                // Si no hay URL, cargar la imagen por defecto
                Glide.with(this)
                        .load(R.drawable.foto_icon)
                        .transform(new CircleCrop())
                        .into(headerProfileImage);
                Log.d("MainActivity", "Cargando imagen de perfil por defecto");
            }
        } else {
            Log.w("MainActivity", "headerProfileImage es null");
        }
    }


    public void checkLoginStatus() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false); // Cambia esto a la clave que uses para el estado de sesión

        // Oculta o muestra los ítems del menú de navegación según el estado de inicio de sesión
        navigationView.getMenu().findItem(R.id.nav_login).setVisible(!isLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_registro).setVisible(!isLoggedIn);

        navigationView.getMenu().findItem(R.id.nav_profile).setVisible(isLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(isLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_cart).setVisible(isLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_dashboard).setVisible(isLoggedIn);
        // Puedes agregar un mensaje o redirigir a otra parte si lo deseas
        if (isLoggedIn) {
            Toast.makeText(this, "Bienvenido de nuevo", Toast.LENGTH_SHORT).show();
        }

        // Forzar actualización del menú
        invalidateOptionsMenu();
        updateSobreNosotrosFragment();
    }

    public void logoutClick() {
        // Limpia los datos de SharedPreferences (esto actualiza el estado de sesión)
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // Elimina todos los datos de sesión
        editor.apply(); // Aplica los cambios (esto es clave)

        // Actualiza el estado de los ítems de menú llamando a checkLoginStatus
        checkLoginStatus();
        // Actualizar el NavigationView para que refleje los cambios
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.drawer_menu);  // Inflar nuevamente el menú
        checkLoginStatus();

        // Resetear la imagen de perfil
        if (headerProfileImage != null) {
            Glide.with(this)
                    .load(R.drawable.foto_icon)
                    .transform(new CircleCrop())
                    .into(headerProfileImage);
        }


        // Restablece el nombre de usuario en el TextView del header (si es necesario)
        if (navHeaderTitle != null) {
            navHeaderTitle.setText("Usuario desconocido");
        } else {
            Log.e("MainActivity", "navHeaderTitle es null en logoutClick()");
        }

        // Muestra mensaje de logout exitoso
        Toast.makeText(this, "Has cerrado tu sesión", Toast.LENGTH_LONG).show();

        // Reemplaza el fragmento actual por el LoginFragment
        replaceFragment(new LoginFragment());

        // Forzar actualización del menú
        invalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus(); // Refresca el menú al volver a la actividad
        loadProfileImage();


    }

    public void updateSobreNosotrosFragment() {
        // Buscar el fragment actual
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof SobreNosotrosFragment) {
            ((SobreNosotrosFragment) currentFragment).updateServicios();
        }
    }

    private void updateNavigationHeaderFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "Usuario Desconocido");

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderTitle = headerView.findViewById(R.id.nav_header_title);

        if (navHeaderTitle != null && !username.isEmpty()) {
            navHeaderTitle.setText(username);
        }

        // NUEVO: También cargar la imagen de perfil aquí
        loadProfileImage();

        // También actualizar el estado del menú
        checkLoginStatus();
    }

}




