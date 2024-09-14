package com.example.proy_mobile2024.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proy_mobile2024.DetalleProductoActivity;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> {

    private Context context;
    private List<Producto> productosList;


    public ProductoAdapter(Context context, List<Producto> productosList) {
        this.context = context;
        this.productosList = productosList;
    }

    public void setProductosList(List<Producto> productosList) {
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

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Producto producto = productosList.get(position);

                    // Crear un Intent para abrir la actividad de detalle
                    Intent intent = new Intent(context, DetalleProductoActivity.class);
                    intent.putExtra("nombre", producto.getNombre());
                    intent.putExtra("descripcion", producto.getDescripcion());
                    intent.putExtra("precio", producto.getPrecio());
                    intent.putExtra("imagen", producto.getImagen());

                    // Iniciar la actividad de detalle
                    context.startActivity(intent);
                }
            });
        }

        public void render(Producto producto) {
            nombre.setText(producto.getNombre());
            precio.setText(String.valueOf(producto.getPrecio()));
            //Glide.with(context).load(producto.getImagen()).into(imagen);
            Glide.with(context)
                    .load(producto.getImagen())
                    .placeholder(R.drawable.placeholder) // Imagen de placeholder mientras carga
                    .error(R.drawable.placeholder) // Imagen de error si falla la carga
                    .into(imagen);
        }

    }
}
