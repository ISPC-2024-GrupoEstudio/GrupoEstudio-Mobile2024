package com.example.proy_mobile2024.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.ItemCarritoData;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.model.Usuario;
import com.example.proy_mobile2024.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> {

    private Context context;
    private List<Producto> productosList;
    private OnProductClickListener listener;

    public ProductoAdapter(Context context, List<Producto> productosList) {
        this.context = context;
        this.productosList = productosList;
        this.listener = listener;
    }

    public void setProductosList(List<Producto> productosList) {
        System.out.println(productosList.size());
        this.productosList = productosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tarjeta_producto, parent, false);
        return new ProductoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoHolder holder, int position) {
        Producto producto = productosList.get(position);
        holder.render(producto);

        // Configuramos el botón "Agregar" para añadir el producto al carrito
        holder.button.setOnClickListener(v -> {
            // Agregar el producto al carrito
            this.agregarProductoAlCarrito(producto);

            // Mostrar un mensaje de confirmación
            Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    public class ProductoHolder extends RecyclerView.ViewHolder {
        private TextView nombre, precio;
        private ImageView imagen;
        private Button button;

        public ProductoHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            precio = itemView.findViewById(R.id.precio);
            imagen = itemView.findViewById(R.id.imagenProducto);
            button = itemView.findViewById(R.id.button);

            button.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    Producto producto = productosList.get(position);
                    listener.onProductClick(producto); // Llamamos a la interfaz para agregar el producto
                }
            });
        }

        public void render(Producto producto) {
            nombre.setText(producto.getNombre());
            precio.setText(String.valueOf(producto.getPrecio()));
            Glide.with(context)
                    .load(producto.getImagen())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imagen);
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Producto producto); // Método que llamará cuando se agregue un producto
    }

    private void agregarProductoAlCarrito(Producto producto) {
        ItemCarritoData item = new ItemCarritoData();
        item.setId_producto(producto.getId_producto());
        item.setCantidad(1);
        item.setNombre_usuario(obtenerUsuarioConectado());

        Call<Void> call = RetrofitClient.getInstance(this.context).getApiService().agregarProductoACarrito(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Producto agregado con exito al carrito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error, datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al agregar el producto, intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String obtenerUsuarioConectado() {
        SharedPreferences preferences = this.context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return preferences.getString("username", null);
    }

    private Context getActivity() {
        return this.context;
    }




}
