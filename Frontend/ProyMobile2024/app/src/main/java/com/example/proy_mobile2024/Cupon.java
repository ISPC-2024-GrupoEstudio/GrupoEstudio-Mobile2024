package com.example.proy_mobile2024;

public class Cupon {
    private int id;
    private String nombre;
    private String descripcion;
    private String tipo_descuento;
    private double valor_descuento;
    private String imagen_url;
    private String fecha_vencimiento;

    // Constructor vacío (necesario para Retrofit)
    public Cupon() {}

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoDescuento() {
        return tipo_descuento;
    }

    public double getValorDescuento() {
        return valor_descuento;
    }

    public String getImagenUrl() {
        return imagen_url;
    }

    public String getFechaVencimiento() {
        return fecha_vencimiento;
    }
}
