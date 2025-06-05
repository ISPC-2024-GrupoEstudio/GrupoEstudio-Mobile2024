package com.example.proy_mobile2024;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

import com.example.proy_mobile2024.services.ApiService;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;

public class CuponesActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout cuponesContainer;
    private Cupon[] cupones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupones);

        initViews();
        //initCuponesData();
        //generateCupones();
        setupListeners();

        cuponesContainer = findViewById(R.id.cupones_container); // Asegúrate de tener este ID en el layout
        cargarCuponesDesdeAPI(); // ¡Aquí se carga todo dinámicamente desde el backend!
    }

    private void initViews() {
        btnBack = findViewById(R.id.btn_back);
        cuponesContainer = findViewById(R.id.cupones_container);
    }
    private void cargarCuponesDesdeAPI() {
        ApiService apiService = RetrofitClient.getInstance(this).getApiService();
        Call<List<Cupon>> call = apiService.obtenerCupones();

        call.enqueue(new Callback<List<Cupon>>() {
            @Override
            public void onResponse(Call<List<Cupon>> call, Response<List<Cupon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cupones = response.body().toArray(new Cupon[0]);
                    generateCupones();  // Aquí sí usamos el diseño existente
                } else {
                    Log.e("CuponesActivity", "Respuesta no exitosa");
                }
            }

            @Override
            public void onFailure(Call<List<Cupon>> call, Throwable t) {
                Log.e("CuponesActivity", "Error al obtener cupones: " + t.getMessage());
            }
        });
    }

//    private void initCuponesData() {
//        cupones = new Cupon[]{
//                new Cupon("32%", "Peludos Abrigados",
//                        new int[]{Color.parseColor("#9C63D6"), Color.parseColor("#5BA3F5")},
//                        Color.parseColor("#8E24AA")),
//
//                new Cupon("12%", "Agosto Gatuno",
//                        new int[]{Color.parseColor("#FF6B35"), Color.parseColor("#F7931E")},
//                        Color.parseColor("#E65100")),
//
//                new Cupon("25%", "Juguetes seleccionados",
//                        new int[]{Color.parseColor("#4FC3F7"), Color.parseColor("#29B6F6")},
//                        Color.parseColor("#0277BD")),
//
//                new Cupon("7%", "Promo Envíos",
//                        new int[]{Color.parseColor("#66BB6A"), Color.parseColor("#4CAF50")},
//                        Color.parseColor("#388E3C"))
//        };
//    }

    private int[] getGradientColorsForIndex(int index) {
        int[][] gradients = new int[][]{
                {Color.parseColor("#9C63D6"), Color.parseColor("#5BA3F5")},
                {Color.parseColor("#FF6B35"), Color.parseColor("#F7931E")},
                {Color.parseColor("#4FC3F7"), Color.parseColor("#29B6F6")},
                {Color.parseColor("#66BB6A"), Color.parseColor("#4CAF50")},
                {Color.parseColor("#FFB300"), Color.parseColor("#F57C00")},
                {Color.parseColor("#AB47BC"), Color.parseColor("#7E57C2")}
        };
        return gradients[index % gradients.length];
    }


//    private void generateCupones() {
//        for (int i = 0; i < cupones.length; i++) {
//            View cuponView = createCuponView(cupones[i], i);
//            cuponesContainer.addView(cuponView);
//        }
//    }
        private void generateCupones() {
            if (cupones == null || cupones.length == 0) {
                Log.w("CuponesActivity", "No hay cupones para mostrar");
                return;
            }

            for (int i = 0; i < cupones.length; i++) {
                View cuponView = createCuponView(cupones[i], i);
                cuponesContainer.addView(cuponView);
            }
        }

    private View createCuponView(Cupon cupon, int index) {
        // Crear el RelativeLayout principal
        RelativeLayout couponLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dpToPx(120)
        );
        layoutParams.setMargins(0, 0, 0, dpToPx(16));
        couponLayout.setLayoutParams(layoutParams);

        // Usar colores según el índice si vienen del backend sin colores
        int[] gradientColors = getGradientColorsForIndex(index);

        // Crear gradiente para el fondo
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                gradientColors
        );
        gradient.setCornerRadius(dpToPx(12));
        couponLayout.setBackground(gradient);
        couponLayout.setElevation(dpToPx(4));

        // Crear sección izquierda (descuento)
        LinearLayout leftSection = createLeftSection(cupon);
        leftSection.setId(View.generateViewId());
        couponLayout.addView(leftSection);

        // Crear sección derecha (detalles)
        LinearLayout rightSection = createRightSection(cupon, index);
        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        rightParams.addRule(RelativeLayout.END_OF, leftSection.getId());
        rightSection.setLayoutParams(rightParams);
        couponLayout.addView(rightSection);

        return couponLayout;
    }

//
//    private View createCuponView(Cupon cupon, int index) {
//        // Crear el RelativeLayout principal
//        RelativeLayout couponLayout = new RelativeLayout(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                dpToPx(120)
//        );
//        layoutParams.setMargins(0, 0, 0, dpToPx(16));
//        couponLayout.setLayoutParams(layoutParams);
//
//        // Crear gradiente para el fondo
//        GradientDrawable gradient = new GradientDrawable(
//                GradientDrawable.Orientation.TL_BR,
//                cupon.getGradientColors()
//        );
//        gradient.setCornerRadius(dpToPx(12));
//        couponLayout.setBackground(gradient);
//        couponLayout.setElevation(dpToPx(4));
//
//        // Crear sección izquierda (descuento)
//        LinearLayout leftSection = createLeftSection(cupon);
//        leftSection.setId(View.generateViewId());
//        couponLayout.addView(leftSection);
//
//        // Crear sección derecha (detalles)
//        LinearLayout rightSection = createRightSection(cupon, index);
//        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT
//        );
//        rightParams.addRule(RelativeLayout.END_OF, leftSection.getId());
//        rightSection.setLayoutParams(rightParams);
//        couponLayout.addView(rightSection);
//
//        return couponLayout;
//    }

    private LinearLayout createLeftSection(Cupon cupon) {
        LinearLayout leftSection = new LinearLayout(this);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
                dpToPx(120),
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        leftSection.setLayoutParams(leftParams);
        leftSection.setOrientation(LinearLayout.VERTICAL);
        leftSection.setGravity(android.view.Gravity.CENTER);

        // Asignar un color según el tipo de descuento
        int leftColor;
        String tipo = cupon.getTipoDescuento();
        if ("PORCENTAJE".equalsIgnoreCase(tipo)) {
            leftColor = Color.parseColor("#4CAF50"); // Verde para porcentaje
        } else if ("MONTO".equalsIgnoreCase(tipo)) {
            leftColor = Color.parseColor("#F44336"); // Rojo para monto
        } else {
            leftColor = Color.parseColor("#2196F3"); // Azul por defecto
        }

        // Fondo sólido para la sección izquierda
        GradientDrawable leftBg = new GradientDrawable();
        leftBg.setColor(leftColor);
        leftBg.setCornerRadii(new float[]{dpToPx(12), dpToPx(12), 0, 0, 0, 0, dpToPx(12), dpToPx(12)});
        leftSection.setBackground(leftBg);

        // Texto "DESCUENTO"
        TextView discountLabel = new TextView(this);
        discountLabel.setText("DESCUENTO");
        discountLabel.setTextColor(Color.WHITE);
        discountLabel.setTextSize(10);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        labelParams.setMargins(0, 0, 0, dpToPx(4));
        discountLabel.setLayoutParams(labelParams);
        leftSection.addView(discountLabel);

        // Porcentaje o monto de descuento
        TextView discountText = new TextView(this);

        double valor = cupon.getValorDescuento();
        String descuentoFormateado = tipo.equalsIgnoreCase("PORCENTAJE")
                ? (int) valor + "%"
                : "$" + (int) valor;

        discountText.setText(descuentoFormateado);
        discountText.setTextColor(Color.WHITE);
        discountText.setTextSize(32);
        discountText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        discountText.setTypeface(null, android.graphics.Typeface.BOLD);
        leftSection.addView(discountText);

        return leftSection;
    }


//    private LinearLayout createLeftSection(Cupon cupon) {
//        LinearLayout leftSection = new LinearLayout(this);
//        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
//                dpToPx(120),
//                RelativeLayout.LayoutParams.MATCH_PARENT
//        );
//        leftSection.setLayoutParams(leftParams);
//        leftSection.setOrientation(LinearLayout.VERTICAL);
//        leftSection.setGravity(android.view.Gravity.CENTER);
//
//        // Fondo sólido para la sección izquierda
//        GradientDrawable leftBg = new GradientDrawable();
//        leftBg.setColor(cupon.getLeftColor());  // Este valor lo agregaste tú aleatoriamente, puedes conservarlo o calcularlo
//        leftBg.setCornerRadii(new float[]{dpToPx(12), dpToPx(12), 0, 0, 0, 0, dpToPx(12), dpToPx(12)});
//        leftSection.setBackground(leftBg);
//
//        // Texto "DESCUENTO"
//        TextView discountLabel = new TextView(this);
//        discountLabel.setText("DESCUENTO");
//        discountLabel.setTextColor(Color.WHITE);
//        discountLabel.setTextSize(10);
//        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        labelParams.setMargins(0, 0, 0, dpToPx(4));
//        discountLabel.setLayoutParams(labelParams);
//        leftSection.addView(discountLabel);
//
//        // Porcentaje o monto de descuento
//        TextView discountText = new TextView(this);
//
//        // Formatear descuento dependiendo del tipo
//        String tipo = cupon.getTipoDescuento(); // "PORCENTAJE" o "MONTO"
//        double valor = cupon.getValorDescuento(); // Por ejemplo, 50.0
//        String descuentoFormateado = tipo.equalsIgnoreCase("PORCENTAJE")
//                ? (int) valor + "%"
//                : "$" + (int) valor;
//
//        discountText.setText(descuentoFormateado);
//        discountText.setTextColor(Color.WHITE);
//        discountText.setTextSize(32);
//        discountText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        discountText.setTypeface(null, android.graphics.Typeface.BOLD);
//        leftSection.addView(discountText);
//
//        return leftSection;
//    }

//    private LinearLayout createLeftSection(Cupon cupon) {
//        LinearLayout leftSection = new LinearLayout(this);
//        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
//                dpToPx(120),
//                RelativeLayout.LayoutParams.MATCH_PARENT
//        );
//        leftSection.setLayoutParams(leftParams);
//        leftSection.setOrientation(LinearLayout.VERTICAL);
//        leftSection.setGravity(android.view.Gravity.CENTER);
//
//        // Fondo sólido para la sección izquierda
//        GradientDrawable leftBg = new GradientDrawable();
//        leftBg.setColor(cupon.getLeftColor());
//        leftBg.setCornerRadii(new float[]{dpToPx(12), dpToPx(12), 0, 0, 0, 0, dpToPx(12), dpToPx(12)});
//        leftSection.setBackground(leftBg);
//
//        // Texto "DESCUENTO"
//        TextView discountLabel = new TextView(this);
//        discountLabel.setText("DESCUENTO");
//        discountLabel.setTextColor(Color.WHITE);
//        discountLabel.setTextSize(10);
//        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        labelParams.setMargins(0, 0, 0, dpToPx(4));
//        discountLabel.setLayoutParams(labelParams);
//        leftSection.addView(discountLabel);
//
//        // Porcentaje de descuento
//        TextView discountText = new TextView(this);
//        discountText.setText(cupon.getDiscount());
//        discountText.setTextColor(Color.WHITE);
//        discountText.setTextSize(32);
//        discountText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        discountText.setTypeface(null, android.graphics.Typeface.BOLD);
//        leftSection.addView(discountText);
//
//        return leftSection;
//    }

    private LinearLayout createRightSection(Cupon cupon, int index) {
        LinearLayout rightSection = new LinearLayout(this);
        rightSection.setOrientation(LinearLayout.VERTICAL);
        rightSection.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

        // Título: nombre del cupón
        TextView tituloText = new TextView(this);
        tituloText.setText(cupon.getNombre());
        tituloText.setTextColor(Color.WHITE);
        tituloText.setTextSize(12);
        LinearLayout.LayoutParams nombreParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        nombreParams.setMargins(0, 0, 0, dpToPx(2));
        tituloText.setLayoutParams(nombreParams);
        rightSection.addView(tituloText);

        // Descripción
        TextView descripcionText = new TextView(this);
        descripcionText.setText(cupon.getDescripcion());
        descripcionText.setTextColor(Color.WHITE);
        descripcionText.setTextSize(10);
        descripcionText.setAlpha(0.8f);
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        descParams.setMargins(0, 0, 0, dpToPx(8));
        descripcionText.setLayoutParams(descParams);
        rightSection.addView(descripcionText);

        // (OPCIONAL) Imagen si quieres mostrarla
    /*
    ImageView imageView = new ImageView(this);
    LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
            dpToPx(100), dpToPx(60)
    );
    imgParams.setMargins(0, 0, 0, dpToPx(8));
    imageView.setLayoutParams(imgParams);
    Picasso.get().load(cupon.getImagenUrl()).into(imageView);
    rightSection.addView(imageView);
    */

        // Contenedor de botones
        LinearLayout buttonsContainer = new LinearLayout(this);
        buttonsContainer.setOrientation(LinearLayout.HORIZONTAL);
        buttonsContainer.setGravity(android.view.Gravity.END);
        rightSection.addView(buttonsContainer);

        // Botón "Aplicar Cupón"
        TextView viewBtn = createButton("Aplicar Cupón", index, true);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        viewParams.setMargins(0, 0, dpToPx(16), 0);
        viewBtn.setLayoutParams(viewParams);
        buttonsContainer.addView(viewBtn);

        return rightSection;
    }

//    private LinearLayout createRightSection(Cupon cupon, int index) {
//        LinearLayout rightSection = new LinearLayout(this);
//        rightSection.setOrientation(LinearLayout.VERTICAL);
//        rightSection.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
//
//        // Titulo
//        TextView tituloText = new TextView(this);
//        tituloText.setText(cupon.getVenue());
//        tituloText.setTextColor(Color.WHITE);
//        tituloText.setTextSize(12);
//        LinearLayout.LayoutParams venueParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        venueParams.setMargins(0, 0, 0, dpToPx(2));
//        tituloText.setLayoutParams(venueParams);
//        rightSection.addView(tituloText);
//
//        // Descripcion cupon
//        TextView descripcionText = new TextView(this);
//        descripcionText.setText("Breve descripcion");
//        descripcionText.setTextColor(Color.WHITE);
//        descripcionText.setTextSize(10);
//        descripcionText.setAlpha(0.8f);
//        LinearLayout.LayoutParams termsParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        termsParams.setMargins(0, 0, 0, dpToPx(8));
//        descripcionText.setLayoutParams(termsParams);
//        rightSection.addView(descripcionText);
//
//
//        // Buttons container
//        LinearLayout buttonsContainer = new LinearLayout(this);
//        buttonsContainer.setOrientation(LinearLayout.HORIZONTAL);
//        buttonsContainer.setGravity(android.view.Gravity.END);
//        rightSection.addView(buttonsContainer);
//
//        // Aplicar cupon button
//        TextView viewBtn = createButton("Aplicar Cupón", index, true);
//        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        viewParams.setMargins(0, 0, dpToPx(16), 0);
//        viewBtn.setLayoutParams(viewParams);
//        buttonsContainer.addView(viewBtn);
//
//
//        return rightSection;
//    }
private TextView createButton(String text, int couponIndex, boolean isViewButton) {
    TextView button = new TextView(this);
    button.setText(text);
    button.setTextColor(Color.WHITE);
    button.setTextSize(12);
    if (!isViewButton) {
        button.setTypeface(null, android.graphics.Typeface.BOLD);
    }
    button.setMinWidth(dpToPx(48));
    button.setMinHeight(dpToPx(48));
    button.setGravity(android.view.Gravity.CENTER);
    button.setClickable(true);
    button.setFocusable(true);

    GradientDrawable border = new GradientDrawable();
    border.setShape(GradientDrawable.RECTANGLE);
    border.setCornerRadius(dpToPx(12)); // Bordes redondeados
    border.setColor(Color.TRANSPARENT); // Fondo transparente
    border.setStroke(dpToPx(2), Color.WHITE); // Borde blanco de 2dp
    button.setBackground(border);
    button.setPadding(dpToPx(24), dpToPx(8), dpToPx(24), dpToPx(8));
    // Click listener
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cupon cupon = cupones[couponIndex];

            if (isViewButton) {
                // Aquí se lanza el Toast de prueba
                Toast.makeText(getApplicationContext(),
                        "Cupón aplicado: " + cupon.getNombre(),
                        Toast.LENGTH_SHORT).show();

                // Más adelante puedes hacer algo como:
                // enviarCuponAlBackend(cupon);
            }
        }
    });

    return button;
}


//    private TextView createButton(String text, int couponIndex, boolean isViewButton) {
//        TextView button = new TextView(this);
//        button.setText(text);
//        button.setTextColor(Color.WHITE);
//        button.setTextSize(12);
//        if (!isViewButton) {
//            button.setTypeface(null, android.graphics.Typeface.BOLD);
//        }
//        button.setMinWidth(dpToPx(48));
//        button.setMinHeight(dpToPx(48));
//        button.setGravity(android.view.Gravity.CENTER);
//        button.setClickable(true);
//        button.setFocusable(true);
//
//        // Set click listener
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isViewButton) {
//                    onViewCupon(cupones[couponIndex]);
//                }
//            }
//        });
//
//        return button;
//    }

    private void setupListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    private void onViewCupon(Cupon cupon) {
//        Toast.makeText(this, "Se aplicó un cupón con " + cupon.getDiscount() + " en " + cupon.getVenue(), Toast.LENGTH_SHORT).show();
//    }
    private void onViewCupon(Cupon cupon) {
        String tipo = cupon.getTipoDescuento();
        double valor = cupon.getValorDescuento();
        String nombre = cupon.getNombre();

        String descuento = tipo.equalsIgnoreCase("PORCENTAJE")
                ? (int) valor + "%"
                : "$" + (int) valor;

        String mensaje = "Se aplicó un cupón de " + descuento + " en " + nombre;
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
}



    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}