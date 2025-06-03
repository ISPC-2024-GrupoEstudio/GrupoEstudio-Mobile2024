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

import com.example.proy_mobile2024.DetallePedidoActivity;
import com.example.proy_mobile2024.R;
import com.example.proy_mobile2024.model.Pedido;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<Pedido> listaPedidos;

    public PedidoAdapter(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_historial_pedido, parent, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);

        holder.textId.setText("Pedido N° " + pedido.getIdPedido());

        // Formatear fecha y hora
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = inputFormat.parse(pedido.getFecha());

            SimpleDateFormat outputDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm");

            holder.textFecha.setText("Fecha: " + outputDate.format(date));
            holder.textHora.setText("Hora: " + outputTime.format(date));
        } catch (ParseException e) {
            holder.textFecha.setText("Fecha: Desconocida");
            holder.textHora.setText("Hora: Desconocida");
        }

        // Estado
        String estado = pedido.getIdEstadoPedido() == 1 ? "Creado"
                : pedido.getIdEstadoPedido() == 2 ? "Finalizado"
                : "Desconocido";
        holder.textEstado.setText("Estado: " + estado);

        // Domicilio
        String domicilio = pedido.getDomicilioEnvio() != null ? pedido.getDomicilioEnvio() : "Domicilio no proporcionado";
        holder.textDomicilio.setText("Domicilio: " + domicilio);

        holder.imgPedido.setImageResource(R.drawable.pedidos_historial);

        //BTN ver detalle
        holder.btnVerDetalles.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetallePedidoActivity.class);
            intent.putExtra("id_pedido", pedido.getIdPedido());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textId, textFecha, textHora, textEstado, textDomicilio;
        ImageView imgPedido;
        Button btnVerDetalles;

        PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.textIdPedido);
            textFecha = itemView.findViewById(R.id.textFechaPedido);
            textHora = itemView.findViewById(R.id.textHoraPedido);
            textEstado = itemView.findViewById(R.id.textEstadoPedido);
            textDomicilio = itemView.findViewById(R.id.textDomicilioPedido);
            imgPedido = itemView.findViewById(R.id.imgPedido);
            btnVerDetalles = itemView.findViewById(R.id.btnVerDetalle);
        }
    }
}
