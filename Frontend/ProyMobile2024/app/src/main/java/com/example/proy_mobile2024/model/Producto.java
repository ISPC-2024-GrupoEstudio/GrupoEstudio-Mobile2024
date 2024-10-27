package com.example.proy_mobile2024.model;

public class Producto {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private double precio;
    private String image_url;
    private int id_categoria_producto; // Nuevo atributo para la categoría

    public Producto(int id_producto, String nombre, String descripcion, double precio, String image_url, int id_categoria_producto) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.image_url = image_url;
        this.id_categoria_producto = id_categoria_producto;
    }

    // Getters y Setters

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return image_url;
    }

    public void setImagen(String imagen) {
        this.image_url = imagen;
    }

    public int getCategoria() {
        return id_categoria_producto;
    }

    public void setCategoria(int id_categoria_producto) {
        this.id_categoria_producto = id_categoria_producto;
    }
}
