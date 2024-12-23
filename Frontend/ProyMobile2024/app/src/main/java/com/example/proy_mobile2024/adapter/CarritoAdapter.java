package com.example.proy_mobile2024.adapter;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoHolder> {

    private Context context;
    private List<Carrito> productosCarrito;
    private String nombreUsuario;

    public CarritoAdapter(Context context, List<Carrito> productosCarrito) {
        this.context = context;
        this.productosCarrito = productosCarrito;
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        this.nombreUsuario = sharedPreferences.getString("username", "");
    }

    public void setCarritoList(List<Carrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoHolder holder, int position) {
        Carrito carrito = productosCarrito.get(position);
        holder.render(carrito);
        holder.btnEliminarItemCarrito.setOnClickListener(v -> {
            eliminarItemCarrito(carrito);
            this.actualizarCarrito();
        });
    }

    @Override
    public int getItemCount() {
        return productosCarrito.size();
    }

    public static class CarritoHolder extends RecyclerView.ViewHolder {
        private TextView nombre, precio;
        private ImageView imagenProducto;
        private ImageView btnEliminarItemCarrito;

        public CarritoHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreCarrito);
            precio = itemView.findViewById(R.id.precioCarrito);
            imagenProducto = itemView.findViewById(R.id.imagenProductoCarrito);
            btnEliminarItemCarrito = itemView.findViewById(R.id.btnEliminarItemCarrito);
        }

        public void render(Carrito carrito) {
            nombre.setText(carrito.getProducto().getNombre());
            precio.setText(String.valueOf(carrito.getProducto().getPrecio()));
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
}
