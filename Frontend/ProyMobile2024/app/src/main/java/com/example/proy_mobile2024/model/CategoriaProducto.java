package com.example.proy_mobile2024.model;

public class CategoriaProducto {
    private int id_categoria_producto;
    private String nombre;
    private String descripcion;

    public int getId_categoria_producto() {
        return id_categoria_producto;
    }

    public void setId_categoria_producto(int id_categoria_producto) {
        this.id_categoria_producto = id_categoria_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
