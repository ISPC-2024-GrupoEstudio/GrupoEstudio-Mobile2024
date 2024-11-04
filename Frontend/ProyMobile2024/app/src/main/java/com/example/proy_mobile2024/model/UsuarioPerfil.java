package com.example.proy_mobile2024.model;

import android.util.Log;

public class UsuarioPerfil {

    private String nombre;
    private String apellido;
    private String nombre_usuario;
    private String email;
    private long telefono;
    private String direccion;
    private long numero_documento;

    public UsuarioPerfil(String nombre,String apellido, String nombre_usuario, String email, long telefono, String direccion, long numero_documento){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.numero_documento = numero_documento;

    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre_nuevo){ this.nombre = nombre_nuevo;}

    public String getApellido(){ return apellido; }

    public void setApellido(String apellido_nuevo){ this.apellido = apellido_nuevo; }

    public String getNombreCompleto(){ return nombre + ' ' + apellido; }

    public String getUser_name(){
        return nombre_usuario;
    }

    public void setUser_name(String user_name_nuevo){
        this.nombre_usuario = user_name_nuevo;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email_nuevo){
        this.email = email_nuevo;
    }

    public long getNro_telefono(){ return telefono; }

    public void setNro_telefono(long nro_telefono_nuevo){ this.telefono = nro_telefono_nuevo;}

    public String getDireccion(){ return direccion;}

    public void setDireccion(String direccion_nueva) {
        this.direccion = direccion_nueva;
    }

    public long getDni(){ return numero_documento;}

    public void setDni(long dni_nuevo){
        this.numero_documento = dni_nuevo;
    }

    @Override
    public String toString(){
        Log.d("123","perfil activo");
        return "Perfil --> " + "nombre = " + nombre+ '|' +
                "nombre usuario = " + nombre_usuario + '|' +
                "email = " + email + '|' +
                "nro telefono = " + telefono + '|' +
                "direccion = " + direccion + '|' +
                "dni = " + numero_documento;
    }
}
