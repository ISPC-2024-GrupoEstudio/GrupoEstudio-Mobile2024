package com.example.proy_mobile2024.model;

public class Perfil {

    private String nombre_apellido;
    private String user_name;
    private String password;
    private String email;
    private int nro_telefono;
    private String direccion;
    private int dni;

    public Perfil(String nombre_apellido, String user_name, String password, String email, int nro_telefono, String direccion, int dni){
        this.nombre_apellido = nombre_apellido;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.nro_telefono = nro_telefono;
        this.direccion = direccion;
        this.dni = dni;

    }

    public String getNombre_apellido(){
        return nombre_apellido;
    }

    public void setNombre_apellido(String nombre_apellido_nuevo){
        this.nombre_apellido = nombre_apellido_nuevo;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name_nuevo){
        this.user_name = user_name_nuevo;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password_nueva){
        this.password = password_nueva;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email_nuevo){
        this.email = email_nuevo;
    }

    public int getNro_telefono(){
        return nro_telefono;
    }

    public void setNro_telefono(int nro_telefono_nuevo){
        this.nro_telefono = nro_telefono_nuevo;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion_nueva) {
        this.direccion = direccion_nueva;
    }

    public int getDni(){
        return dni;
    }

    public void setDni(int dni_nuevo){
        this.dni = dni_nuevo;
    }

    @Override
    public String toString(){
        return "Perfil --> " + "nombre y apellido = " + nombre_apellido + '|' +
                "nombre usuario = " + user_name + '|' +
                "contrasena = " + password + '|' +
                "email = " + email + '|' +
                "nro telefono = " + nro_telefono + '|' +
                "direccion = " + direccion + '|' +
                "dni = " + dni;
    }
}
