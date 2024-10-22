package com.example.proy_mobile2024.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.DetalleProductoActivity;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.Producto;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoHolder> {

    private Context context;
    private List<Producto> productosCarrito;

    public CarritoAdapter(Context context, List<Producto> productosCarrito) {
        this.context = context;
        this.productosCarrito = productosCarrito;
    }

    @NonNull
    @Override
    public CarritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoHolder holder, int position) {
        Producto producto = productosCarrito.get(position);
        holder.render(producto);
    }

    @Override
    public int getItemCount() {
        return productosCarrito.size();
    }

    public static class CarritoHolder extends RecyclerView.ViewHolder {
        private TextView nombre, precio;
        private ImageView imagenProducto;

        public CarritoHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreCarrito);
            precio = itemView.findViewById(R.id.precioCarrito);
            imagenProducto = itemView.findViewById(R.id.imagenProductoCarrito);
        }

        public void render(Producto producto) {
            nombre.setText(producto.getNombre());
            precio.setText(String.valueOf(producto.getPrecio()));
            Glide.with(itemView.getContext())
                    .load(producto.getImagen())
                    .placeholder(R.drawable.placeholder)
                    .into(imagenProducto);
        }
    }
}
