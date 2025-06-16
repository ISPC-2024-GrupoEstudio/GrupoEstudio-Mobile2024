package com.example.proy_mobile2024;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.adapter.CarritoAdapter;
import com.example.proy_mobile2024.model.Carrito;
import com.example.proy_mobile2024.model.CostoEnvioRequest;
import com.example.proy_mobile2024.model.CostoEnvioResponse;
import com.example.proy_mobile2024.model.PedidoCheckoutData;
import com.example.proy_mobile2024.model.PedidoCheckoutItemData;
import com.example.proy_mobile2024.model.PreferenciaResponse;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.model.Sucursal;
import com.example.proy_mobile2024.services.CorreoArgentinoApi;
import com.example.proy_mobile2024.services.RetrofitClient;
import com.example.proy_mobile2024.services.RetrofitClientCorreo;
import com.example.proy_mobile2024.viewsmodels.ProvinciaUtils;
import com.google.gson.Gson;

import org.cloudinary.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCTOS = "productos_carrito";

    private RecyclerView recyclerCheckout;
    private CarritoAdapter carritoAdapter;
    private List<Carrito> listaCarrito;

    private TextView tvTotal;
    private RecyclerView recyclerView;
    private ArrayList<Producto> productos;
    private ImageButton btnVolver;
    private Button btnPagar;
    private TextView tvCostoEnvio;
    private EditText etCodigoPostal;
    private RadioGroup rgEnvio;
    private RadioButton rbDomicilio, rbSucursal;
    private Spinner spinnerSucursales;
    private Button btnCalcularEnvio;
    private List<Sucursal> sucursalesList;

    private double costoEnvio = 0.0;
    private double totalProductos = 0.0;

    private String provinciaOrigen = "AR-X";  // fijo, según lo que dijiste
    private String codigoPostalOrigen = "5000"; // ejemplo, podés cambiarlo o pedirlo dinámico
    private TextView tvPrecioSucursal;
    private TextView tvPrecioDomicilio;
    private Sucursal sucursalSeleccionada = null;
    LinearLayout layoutDireccion;
    EditText etDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerCheckout = findViewById(R.id.recyclerProductos);
        recyclerCheckout.setLayoutManager(new LinearLayoutManager(this));

        listaCarrito = new ArrayList<>();
        tvTotal = findViewById(R.id.tvTotal);
        double total = 0;
        tvCostoEnvio = findViewById(R.id.tvCostoEnvio);
        etCodigoPostal = findViewById(R.id.etCodigoPostal);
        rgEnvio = findViewById(R.id.rgEnvio);
        rbDomicilio = findViewById(R.id.rbDomicilio);
        rbSucursal = findViewById(R.id.rbSucursal);
        spinnerSucursales = findViewById(R.id.spinnerSucursales);
        btnCalcularEnvio = findViewById(R.id.btnCalcularEnvio);
        tvPrecioSucursal = findViewById(R.id.tvPrecioSucursal);
        tvPrecioDomicilio = findViewById(R.id.tvPrecioDomicilio);
        etDireccion = findViewById(R.id.etDireccion);
        layoutDireccion = findViewById(R.id.layoutDireccion);




        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCTOS)) {
            productos = (ArrayList<Producto>) intent.getSerializableExtra(EXTRA_PRODUCTOS);

            if(productos != null){
                Log.d("Checkout", "Productos recibidos desde Intent: " + productos.size());
            } else {
                Log.d("Checkout", "productos es null");
            }

            for (Producto producto : productos) {
                Log.d("Checkout", "Producto: " + producto.getNombre()); // o cualquier atributo para verificar
                Carrito item = new Carrito();
                item.setProducto(producto);
                item.setCantidad(1); // O la cantidad real
                listaCarrito.add(item);
            }

            Log.d("Checkout", "listaCarrito después de agregar productos: " + listaCarrito.size());


            carritoAdapter = new CarritoAdapter(this, listaCarrito, true);
            recyclerCheckout.setAdapter(carritoAdapter);
        } else {
            // Si no se pasan productos, cargamos desde backend
            carritoAdapter = new CarritoAdapter(this, listaCarrito, true);
            recyclerCheckout.setAdapter(carritoAdapter);
            cargarCarrito();
        }

        for (Carrito item : listaCarrito) {
            double precio = item.getProducto().getPrecio(); // o getPrecioUnitario()
            int cantidad = item.getCantidad();
            total += precio * cantidad;
        }
        totalProductos = total;

        tvTotal.setText(String.format("Total: $%.2f", total));

        // Mostrar u ocultar spinner según tipo de envío
        rgEnvio.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbSucursal) {
                String cpDestino = etCodigoPostal.getText().toString().trim();
                if (!cpDestino.isEmpty()) {
                    String codProvinciaDestino = ProvinciaUtils.determinarProvinciaDesdeCP(cpDestino);
                    if (!codProvinciaDestino.equals("Provincia no identificada")) {
                        cargarSucursales(codProvinciaDestino);
                    } else {
                        Toast.makeText(this, "Código postal no reconocido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Ingrese el código postal para cargar sucursales", Toast.LENGTH_SHORT).show();
                }
                spinnerSucursales.setVisibility(View.VISIBLE);
            } else {
                spinnerSucursales.setVisibility(View.GONE);
                sucursalSeleccionada = null;
            }
            costoEnvio = 0;
            actualizarTotal();
        });

        // Selección de sucursal en el spinner
        spinnerSucursales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (sucursalesList != null && position >= 0 && position < sucursalesList.size()) {
                    sucursalSeleccionada = sucursalesList.get(position);
                    Log.d("Checkout", "Sucursal seleccionada: " + sucursalSeleccionada.getNombreSucursal());
                } else {
                    Log.d("Checkout", "Sucursal no seleccionada o lista vacía");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sucursalSeleccionada = null;
            }
        });

        // Botón para calcular costo de envío
        btnCalcularEnvio.setOnClickListener(v -> {
            String cpDestino = etCodigoPostal.getText().toString().trim();

            if (cpDestino.isEmpty() || cpDestino.length() < 4) {
                Toast.makeText(CheckoutActivity.this, "Ingrese un código postal válido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Determinar código de provincia desde el CP
            String codProvinciaDestino = ProvinciaUtils.determinarProvinciaDesdeCP(cpDestino);
            String nombreProvinciaDestino = ProvinciaUtils.obtenerNombreProvincia(codProvinciaDestino);

            if (nombreProvinciaDestino.equals("Provincia no identificada")) {
                Toast.makeText(CheckoutActivity.this, "Código postal no reconocido", Toast.LENGTH_SHORT).show();
                return;
            }

            // MOSTRAR FORMULARIO DE DIRECCIÓN si elige "A domicilio"
            if (rbDomicilio.isChecked()) {
                layoutDireccion.setVisibility(View.VISIBLE);
            } else {
                layoutDireccion.setVisibility(View.GONE);
            }

            // Realiza el cálculo de envío
            String peso = "1";
            calcularCostoEnvio(cpDestino, peso);
        });







        btnVolver = findViewById(R.id.btnVolverCart);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la actividad de productos
                Intent intent = new Intent(CheckoutActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

        btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar solo si es envío a domicilio
                if (rbDomicilio.isChecked()) {
                    EditText etDireccion = findViewById(R.id.etDireccion);
                    String direccion = etDireccion.getText().toString().trim();

                    if (direccion.isEmpty()) {
                        Toast.makeText(CheckoutActivity.this, "Debés ingresar una dirección para el envío a domicilio.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Si pasa validación, continúa al checkout de Mercado Pago
                checkout();
            }
        });

    }

    private void checkout() {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String nombre_usuario = sharedPreferences.getString("id_usuario", "");

        EditText etDireccion = findViewById(R.id.etDireccion);
        String direccion = etDireccion.getText().toString().trim();
        String codigoPostal = etCodigoPostal.getText().toString().trim();

        if (rbDomicilio.isChecked() && direccion.isEmpty()) {
            Toast.makeText(CheckoutActivity.this, "Debés ingresar una dirección para el envío a domicilio.", Toast.LENGTH_SHORT).show();
            return;
        }

        String ciudad = ProvinciaUtils.obtenerNombreProvincia(ProvinciaUtils.determinarProvinciaDesdeCP(codigoPostal));
        if (ciudad.equals("Provincia no identificada")) {
            Toast.makeText(this, "Código postal no reconocido", Toast.LENGTH_SHORT).show();
            return;
        }

        String tipoEnvio = rbDomicilio.isChecked() ? "Domicilio" : "Sucursal";

        JSONObject opcionEnvio = new JSONObject();
        opcionEnvio.put("tipo", tipoEnvio);
        opcionEnvio.put("costo", costoEnvio);
        if (tipoEnvio.equals("Sucursal") && sucursalSeleccionada != null) {
            opcionEnvio.put("id", sucursalSeleccionada.getCodigoSucursal());
            opcionEnvio.put("nombreSucursal", sucursalSeleccionada.getNombreSucursal());
        }

        double totalFinal = totalProductos + costoEnvio;
        double descuento = 0.0; // Podés modificar esto si estás manejando cupones

        // Armamos el external_reference completo
        String externalReference = nombre_usuario + "|" + direccion + "|" + codigoPostal + "|" +
                opcionEnvio.toString() + "|" + totalFinal + "|" + (rbDomicilio.isChecked() ? 1 : 2) + "|" + ciudad + "|" + descuento;

        Log.d("Checkout", "external_reference: " + externalReference);

        // Limpiamos ítems anteriores de envío, si los hubiera
        List<Carrito> listaConEnvio = new ArrayList<>(listaCarrito);
        for (Iterator<Carrito> iterator = listaConEnvio.iterator(); iterator.hasNext(); ) {
            Carrito item = iterator.next();
            if ("Costo de envío".equals(item.getProducto().getNombre())) {
                iterator.remove();
            }
        }

        // Agregamos el costo de envío como ítem si es mayor a cero
        if (costoEnvio > 0) {
            Carrito envioItem = new Carrito();
            Producto envio = new Producto(
                    -1,
                    "Costo de envío",
                    "Tarifa de envío seleccionada",
                    costoEnvio,
                    "",
                    -1
            );
            envioItem.setProducto(envio);
            envioItem.setCantidad(1);
            listaConEnvio.add(envioItem);
        }

        PedidoCheckoutData pedidoCheckoutData = new PedidoCheckoutData();
        pedidoCheckoutData.setExternal_reference(externalReference);
        pedidoCheckoutData.setItemsCarrito(listaConEnvio);

        RetrofitClient.getInstance(this).getApiService().obtenerPreferencia(pedidoCheckoutData).enqueue(new Callback<PreferenciaResponse>() {
            @Override
            public void onResponse(Call<PreferenciaResponse> call, Response<PreferenciaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(response.body().getInit_point()));
                    startActivity(intent);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Error al obtener preferencia de pago", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PreferenciaResponse> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarCarrito() {
        SharedPreferences preferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        RetrofitClient.getInstance(this).getApiService().obtenerCarrito(username).enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaCarrito = response.body();
                    carritoAdapter.setCarritoList(listaCarrito);
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error al cargar el carrito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarTotal() {
        double totalFinal = totalProductos + costoEnvio;
        tvTotal.setText(String.format("Total: $%.2f", totalFinal));
        tvCostoEnvio.setText(String.format("Costo de envío: $%.2f", costoEnvio));
        tvCostoEnvio.setVisibility(View.GONE);

    }

    private void cargarSucursales(String provincia) {
        Log.d("SUCURSALES", "Buscando sucursales para provincia: " + provincia);

        CorreoArgentinoApi api = RetrofitClientCorreo.getApi();
        api.obtenerSucursales(provincia).enqueue(new Callback<List<Sucursal>>() {
            @Override
            public void onResponse(Call<List<Sucursal>> call, Response<List<Sucursal>> response) {
                Log.d("SUCURSALES", "Respuesta recibida. Código: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    sucursalesList = response.body(); // Asignar a variable de clase

                    Log.d("SUCURSALES", "Sucursales recibidas: " + sucursalesList.size());
                    for (Sucursal suc : sucursalesList) {
                        Log.d("SUCURSALES", "Sucursal: " + suc.getNombreSucursal());
                    }

                    List<String> nombres = new ArrayList<>();
                    for (Sucursal suc : sucursalesList) {
                        nombres.add(suc.getNombreSucursal());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            CheckoutActivity.this,
                            android.R.layout.simple_spinner_item,
                            nombres
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSucursales.setAdapter(adapter);
                    spinnerSucursales.setVisibility(View.VISIBLE);

                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
                        Log.e("SUCURSALES", "Error en respuesta: " + errorBody);
                    } catch (IOException e) {
                        Log.e("SUCURSALES", "Error leyendo errorBody", e);
                    }
                    Toast.makeText(CheckoutActivity.this, "No se pudieron cargar las sucursales", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sucursal>> call, Throwable t) {
                Log.e("SUCURSALES", "Falla en la llamada: " + t.getMessage(), t);
                Toast.makeText(CheckoutActivity.this, "Error al cargar sucursales: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calcularCostoEnvio(String cpDestino, String peso) {
        String cpOrigen = "5000"; // Córdoba Capital
        String provinciaOrigen = "AR-X"; // Córdoba
        String provinciaDestino = ProvinciaUtils.determinarProvinciaDesdeCP(cpDestino);

        CostoEnvioRequest request = new CostoEnvioRequest(cpOrigen, cpDestino, provinciaOrigen, provinciaDestino, peso);

        // DEBUG
        Log.d("DEBUG", "CP origen: " + request.getCpOrigen());
        Log.d("DEBUG", "CP destino: " + request.getCpDestino());
        Log.d("DEBUG", "Prov origen: " + request.getProvinciaOrigen());
        Log.d("DEBUG", "Prov destino: " + request.getProvinciaDestino());
        Log.d("DEBUG", "Peso: " + request.getPeso());

        CorreoArgentinoApi api = RetrofitClientCorreo.getApi();
        Call<CostoEnvioResponse> call = api.calcularCostoEnvio(request);
        call.enqueue(new Callback<CostoEnvioResponse>() {
            @Override
            public void onResponse(Call<CostoEnvioResponse> call, Response<CostoEnvioResponse> response) {
                if (response.isSuccessful()) {
                    CostoEnvioResponse costo = response.body();
                    if (costo != null && costo.getPaqarClasico() != null) {
                        double precioSucursal = costo.getPaqarClasico().getASucursal();
                        double precioDomicilio = costo.getPaqarClasico().getADomicilio();

                        int selectedId = rgEnvio.getCheckedRadioButtonId();

                        if (selectedId == R.id.rbDomicilio) {
                            costoEnvio = precioDomicilio; // Guardar costo
                            tvPrecioDomicilio.setText("Precio a domicilio: $" + precioDomicilio);
                            tvPrecioDomicilio.setVisibility(View.VISIBLE);
                        } else if (selectedId == R.id.rbSucursal) {
                            costoEnvio = precioSucursal; // Guardar costo
                            tvPrecioDomicilio.setText("Precio en sucursal: $" + precioSucursal);
                            tvPrecioDomicilio.setVisibility(View.VISIBLE);
                        }

                        for (Iterator<Carrito> iterator = listaCarrito.iterator(); iterator.hasNext(); ) {
                            Carrito item = iterator.next();
                            if (item.getProducto().getNombre().equals("Costo de envío")) {
                                iterator.remove();
                            }
                        }

                        Producto envio = new Producto(
                                -1,
                                "Costo de envío",
                                "Tarifa de envío seleccionada",
                                costoEnvio,
                                "",
                                -1
                        );

                        Carrito itemEnvio = new Carrito();
                        itemEnvio.setProducto(envio);
                        itemEnvio.setCantidad(1);

                        listaCarrito.add(itemEnvio);

                        carritoAdapter.setCarritoList(listaCarrito);
                        carritoAdapter.notifyDataSetChanged();

                        actualizarTotal();

                    } else {
                        Log.e("Envio", "Objeto paqarClasico es null o respuesta vacía");
                    }
                } else {
                    Log.e("Envio", "Error en respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CostoEnvioResponse> call, Throwable t) {
                Log.e("Envio", "Falla en la llamada: " + t.getMessage());
            }
        });
    }
}