package com.example.proy_mobile2024;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.flexbox.FlexboxLayout;

public class SobreNosotrosFragment extends Fragment {

    private FlexboxLayout flexboxServicios;
    private WebView webViewMap;
    private boolean isUserLoggedIn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre_nosotros, container, false);

        // Inicializar vistas
        initViews(view);

        // Verificar estado de sesión del usuario
        checkUserLoginStatus();

        // Configurar servicios
        setupServicios();

        // Configurar mapa
        setupMap();

        return view;
    }

    private void initViews(View view) {
        flexboxServicios = view.findViewById(R.id.flexbox_servicios);
        webViewMap = view.findViewById(R.id.webview_map);

        // Configurar click en email de soporte
        TextView emailSoporte = view.findViewById(R.id.textView_email_soporte);
        emailSoporte.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:soporte@petboutique.com"));
            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                // Manejar error si no hay app de email
            }
        });
    }

    private void checkUserLoginStatus() {
        // Verificar si el usuario está logueado usando SharedPreferences
        SharedPreferences prefs = getActivity().getSharedPreferences("AuthPrefs", getActivity().MODE_PRIVATE);
        isUserLoggedIn = prefs.getBoolean("isLoggedIn", false);
    }

    private void setupServicios() {
        flexboxServicios.removeAllViews();

        if (!isUserLoggedIn) {
            // Servicios para usuario no logueado
            addServiceCard("Galería", R.drawable.ic_servicios_galeria, R.color.celeste, () -> {
                // Navegar a galería de productos
                Intent intent = new Intent(getActivity(), GaleriaProductosActivity.class);
                startActivity(intent);
            });

            addServiceCard("Iniciar Sesión", R.drawable.ic_servicios_login, R.color.violetaClaro, () -> {
                // Navegar a fragment de login
                navigateToFragment(new LoginFragment());
            });

            addServiceCard("Registro", R.drawable.ic_servicios_registro, R.color.verdeAgua, () -> {
                // Navegar a fragment de registro
                navigateToFragment(new RegisterFragment());
            });

            addServiceCard("Contacto", R.drawable.ic_servicios_contaco, R.color.celeste, () -> {
                // Navegar a fragment de contacto
                navigateToFragment(new ContactoFragment());
            });

            addServiceCard("Página Web", R.drawable.ic_servicios_web, R.color.violetaClaro, () -> {
                // Abrir página web en el navegador
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ISPC-2024-GrupoEstudio/GrupoEstudio-2024"));
                startActivity(webIntent);
            });

        } else {
            // Servicios para usuario logueado
            addServiceCard("Galería", R.drawable.ic_servicios_galeria, R.color.celeste, () -> {
                // Navegar a galería de productos
                Intent intent = new Intent(getActivity(), GaleriaProductosActivity.class);
                startActivity(intent);
            });

            addServiceCard("Perfil", R.drawable.ic_servicios_perfil, R.color.violetaClaro, () -> {
                // Navegar a actividad de perfil
                Intent intent = new Intent(getActivity(), PerfilActivity.class);
                startActivity(intent);
            });

            addServiceCard("Dashboard", R.drawable.ic_servicios_dash, R.color.verdeAgua, () -> {
                // Navegar a actividad de dashboard
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            });

            addServiceCard("Carrito", R.drawable.ic_servicios_carrito, R.color.celeste, () -> {
                // Navegar a actividad de carrito
                Intent intent = new Intent(getActivity(), CarritoActivity.class);
                startActivity(intent);
            });

            addServiceCard("Contacto", R.drawable.ic_servicios_contaco, R.color.violetaClaro, () -> {
                // Navegar a fragment de contacto
                navigateToFragment(new ContactoFragment());
            });

            addServiceCard("Cerrar Sesión", R.drawable.ic_servicios_salir, R.color.verdeAgua, () -> {
                // Cerrar sesión usando el método de MainActivity
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).logoutClick();
                } else {
                    // Fallback si no se puede acceder al método de MainActivity
                    logout();
                }
            });
        }
    }

    private void navigateToFragment(Fragment fragment) {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            // Usar el método replaceFragment de MainActivity para mantener consistencia
            MainActivity mainActivity = (MainActivity) getActivity();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void addServiceCard(String title, int iconRes, int colorRes, Runnable onClickAction) {
        // Crear el card
        CardView cardView = new CardView(getContext());
        FlexboxLayout.LayoutParams cardParams = new FlexboxLayout.LayoutParams(
                dpToPx(150), dpToPx(150)
        );
        cardParams.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4));
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(dpToPx(12));
        cardView.setCardElevation(dpToPx(4));
        cardView.setUseCompatPadding(true);

        // Crear el layout interno
        android.widget.LinearLayout innerLayout = new android.widget.LinearLayout(getContext());
        innerLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        innerLayout.setGravity(android.view.Gravity.CENTER);
        innerLayout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
        innerLayout.setBackgroundColor(getResources().getColor(colorRes));

        // Crear el ícono
        ImageView icon = new ImageView(getContext());
        android.widget.LinearLayout.LayoutParams iconParams = new android.widget.LinearLayout.LayoutParams(
                dpToPx(32), dpToPx(32)
        );
        iconParams.bottomMargin = dpToPx(8);
        icon.setLayoutParams(iconParams);
        icon.setImageResource(iconRes);
        icon.setColorFilter(getResources().getColor(android.R.color.white));

        // Crear el texto
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(20);
        textView.setGravity(android.view.Gravity.CENTER);
        // textView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.poppins_medium));

        // Agregar vistas al layout interno
        innerLayout.addView(icon);
        innerLayout.addView(textView);
        cardView.addView(innerLayout);

        // Configurar click
        cardView.setOnClickListener(v -> {
            if (onClickAction != null) {
                onClickAction.run();
            }
        });

        // Agregar al flexbox
        flexboxServicios.addView(cardView);
    }

    private void setupMap() {
        webViewMap.getSettings().setJavaScriptEnabled(true);

        String mapHtml = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "</head>" +
                "<body style='margin:0;padding:0;'>" +
                "<iframe src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3404.5486!2d-64.1945594!3d-31.410384!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9432987e1d449115%3A0x97882a527eaebab1!2sVeterinaria%20Alem%20Sucursal%20Col%C3%B3n!5e0!3m2!1ses-419!2sar!4v1234567890' " +
                "width='100%' height='auto' style='border:0;' allowfullscreen='' loading='lazy'></iframe>" +
                "</body>" +
                "</html>";

        webViewMap.loadData(mapHtml, "text/html", "UTF-8");
    }

    private void logout() {
        // Limpiar sesión
        SharedPreferences prefs = getActivity().getSharedPreferences("AuthPrefs", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Limpiar todos los datos como lo hace MainActivity
        editor.apply();

        // Actualizar servicios
        isUserLoggedIn = false;
        setupServicios();

        // Navegar al fragment de login
        navigateToFragment(new LoginFragment());

        // Mostrar mensaje de confirmación
        android.widget.Toast.makeText(getContext(), "Sesión cerrada exitosamente", android.widget.Toast.LENGTH_SHORT).show();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Verificar si cambió el estado de sesión al volver al fragment
        boolean previousLoginStatus = isUserLoggedIn;
        checkUserLoginStatus();
        if (previousLoginStatus != isUserLoggedIn) {
            setupServicios();
        }
    }

    public void updateServicios() {
        // Verificar el estado actual de sesión
        checkUserLoginStatus();
        // Actualizar los servicios mostrados
        setupServicios();
    }
}