package com.example.proy_mobile2024.adapter;
import static android.content.Context.MODE_PRIVATE;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.DetalleProductoActivity;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.Carrito;
import com.example.proy_mobile2024.model.ItemCarritoData;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoHolder> {

    private Context context;
    private List<Carrito> productosCarrito;
    private String nombreUsuario;

    private boolean esModoCheckout;

    public CarritoAdapter(Context context, List<Carrito> productosCarrito, boolean esModoCheckout) {
        this.context = context;
        this.productosCarrito = productosCarrito != null ? productosCarrito : new ArrayList<>();
        this.esModoCheckout = esModoCheckout;
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        this.nombreUsuario = sharedPreferences.getString("username", "");
    }

    public void setCarritoList(List<Carrito> productosCarrito) {
        this.productosCarrito = productosCarrito != null ? productosCarrito : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoHolder(view);
    }

    public List<Carrito> getItems() {
        return productosCarrito;
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoHolder holder, int position) {
        Carrito carrito = productosCarrito.get(position);
        holder.render(carrito);

        if (esModoCheckout) {
            holder.btnEliminarItemCarrito.setVisibility(View.GONE);
            holder.btnSumarCantidad.setVisibility(View.GONE);
            holder.btnRestarCantidad.setVisibility(View.GONE);
        } else {
            holder.btnEliminarItemCarrito.setVisibility(View.VISIBLE);
            holder.btnEliminarItemCarrito.setOnClickListener(v -> {
                eliminarItemCarrito(carrito);
                this.actualizarCarrito();
            });
            //boton mas
            holder.btnSumarCantidad.setVisibility(View.VISIBLE);
            holder.btnSumarCantidad.setOnClickListener(v -> {
                agregarProductoAlCarrito(carrito);
                this.actualizarCarrito();
            });
            //boton menos
            holder.btnRestarCantidad.setVisibility(View.VISIBLE);
            holder.btnRestarCantidad.setOnClickListener(v -> {
                quitarItemAlCarrito(carrito);
                this.actualizarCarrito();
            });
        }
    }

    @Override
    public int getItemCount() {
        return productosCarrito != null ? productosCarrito.size() : 0;
    }

    public static class CarritoHolder extends RecyclerView.ViewHolder {
        public ImageButton btnSumarCantidad;
        public ImageButton btnRestarCantidad;
        private TextView nombre, precio, textCantidad;
        private ImageView imagenProducto;
        private ImageView btnEliminarItemCarrito;

        public CarritoHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreCarrito);
            precio = itemView.findViewById(R.id.precioCarrito);
            textCantidad = itemView.findViewById(R.id.textCantidad);
            imagenProducto = itemView.findViewById(R.id.imagenProductoCarrito);
            btnEliminarItemCarrito = itemView.findViewById(R.id.btnEliminarItemCarrito);
            btnSumarCantidad = itemView.findViewById(R.id.btnSumarCantidad);
            btnRestarCantidad = itemView.findViewById(R.id.btnRestarCantidad);
        }

        public void render(Carrito carrito) {
            nombre.setText(carrito.getProducto().getNombre());
            precio.setText(String.valueOf(carrito.getProducto().getPrecio()));
            textCantidad.setText(String.valueOf(carrito.getCantidad()));
            Glide.with(itemView.getContext())
                    .load(carrito.getProducto().getImagen())
                    .placeholder(R.drawable.placeholder)
                    .into(imagenProducto);
        }
    }

    private void eliminarItemCarrito(Carrito carrito) {
        Call<Void> call = RetrofitClient.getInstance(this.context).getApiService().eliminarDeCarrito(carrito.getId_carrito());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Producto eliminado del carrito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al eliminar el producto del carrito, intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Context getActivity() {
        return this.context;
    }

    public void actualizarCarrito() {
        RetrofitClient.getInstance(this.context).getApiService().obtenerCarrito(this.nombreUsuario).enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println();
                    System.out.println(" -- carrito --");
                    System.out.println(" >> la respuesta es: " + response.body());
                    List<Carrito> listaCarrito = response.body();
                    System.out.println(">> la lista es: " + listaCarrito);
                    setCarritoList(listaCarrito);
                } else {
                    System.out.println("Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    public void vaciarCarrito() {
        productosCarrito.forEach(carrito -> {
            eliminarItemCarrito(carrito);
        });
        this.actualizarCarrito();
    }

    public List<Producto> getListProducts() {
        List<Producto> lista = new ArrayList<>();
        for (Carrito item : productosCarrito) {
            lista.add(item.getProducto());
        }
        return lista;
    }

    private void agregarProductoAlCarrito(Carrito carrito) {
        ItemCarritoData item = new ItemCarritoData();
        item.setId_producto(carrito.getId_producto());
        item.setCantidad(1);
        item.setNombre_usuario(carrito.getNombre_usuario());

        Call<Void> call = RetrofitClient.getInstance(context).getApiService().agregarProductoACarrito(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Producto agregado con exito al carrito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error al agregar el producto, intente nuevamente", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void quitarItemAlCarrito(Carrito carrito) {
        ItemCarritoData item = new ItemCarritoData();
        item.setId_producto(carrito.getId_producto());
        item.setCantidad(1);
        item.setNombre_usuario(carrito.getNombre_usuario());


        Call<Void> call = RetrofitClient.getInstance(context).getApiService().eliminarItemDeCarrito(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Producto eliminado con exito del carrito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error al agregar el producto, intente nuevamente", Toast.LENGTH_LONG).show();
            }
        });
    }
}
