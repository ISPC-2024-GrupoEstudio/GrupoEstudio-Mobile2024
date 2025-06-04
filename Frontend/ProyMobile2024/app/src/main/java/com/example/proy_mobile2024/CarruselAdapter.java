package com.example.proy_mobile2024;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Context;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.CarouselViewHolder> {

    List<CarruselDashboard> items;

    public CarruselAdapter(List<CarruselDashboard> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrusel_dashboard, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        CarruselDashboard item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.image.setImageResource(item.getImageResId());
        holder.button.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, GaleriaProductosActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        Button button;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tituloCardCarrusel);
            image = itemView.findViewById(R.id.imgCardCarrusel);
            button = itemView.findViewById(R.id.btnCardCarrusel);
        }
    }
}
