package com.example.proy_mobile2024.model;

public class ProductosXPedido {
    private int id;
    private int cantidad;
    private double precio;
    private Producto id_producto;

    public int getId(){ return id; }

    public int getCantidad(){ return cantidad; }

    public double getPrecio(){ return precio; }

    public Producto getId_producto(){ return id_producto; }
}
