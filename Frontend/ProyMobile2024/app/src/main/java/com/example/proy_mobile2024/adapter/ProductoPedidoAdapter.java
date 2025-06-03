package com.example.proy_mobile2024.adapter;
import com.example.proy_mobile2024.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.model.ProductosXPedido;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

public class ProductoPedidoAdapter extends RecyclerView.Adapter<ProductoPedidoAdapter.ViewHolder> {

    private List<ProductosXPedido> productoXPedidoList = new ArrayList<>();

    public void setProductoXPedidoList(List<ProductosXPedido> lista){
        this.productoXPedidoList = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoPedidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_pedido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoPedidoAdapter.ViewHolder holder, int position){
        ProductosXPedido item = productoXPedidoList.get(position);
        Producto producto = item.getId_producto();

        holder.tvNombre.setText(producto.getNombre());
        holder.tvCantidad.setText("Cantidad: " + item.getCantidad());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
        double subtotal = item.getCantidad() * producto.getPrecio();
        holder.tvSubtotal.setText("Subtotal: $" + String.format("%.2f", subtotal));

        if (producto.getImagen() != null && !producto.getImagen().isEmpty()){
            Picasso.get().load(producto.getImagen()).into(holder.imgProducto);
        }else{
            holder.imgProducto.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public int getItemCount(){
        return productoXPedidoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre, tvCantidad, tvPrecio, tvSubtotal;
        ImageView imgProducto;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreProd);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
            imgProducto = itemView.findViewById(R.id.ivProducto);

        }
    }
}
