package com.example.proy_mobile2024.model;

public class Usuario {
    private String nombre;
    private String apellido;
    private int id_tipo_documento;
    private int numero_documento;
    private String nombreUsuario;
    private String email;
    private String password;

    public Usuario(String nombre, String apellido, int id_tipo_documento, int numero_documento, String nombreUsuario , String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_tipo_documento = id_tipo_documento;
        this.numero_documento = numero_documento;
        this.nombreUsuario  = nombreUsuario ;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(int id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public int getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(int numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombreUsuario() {
        return nombreUsuario ;
    }

    public void setNombreUsuario(String nombreUsuario ) {
        this.nombreUsuario  = nombreUsuario ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


