package com.example.proy_mobile2024.model;

//public class MisCuponRequest {
//    private String nombre_usuario;
//    private String cupon_nombre;
//
//    public MisCuponRequest(String nombre_usuario, String cupon_nombre) {
//        this.nombre_usuario = nombre_usuario;
//        this.cupon_nombre = cupon_nombre;
//    }
//
//    public String getNombre_usuario() {
//        return nombre_usuario;
//    }
//
//    public void setNombre_usuario(String nombre_usuario) {
//        this.nombre_usuario = nombre_usuario;
//    }
//
//    public String getCupon_nombre() {
//        return cupon_nombre;
//    }
//
//    public void setCupon_nombre(String cupon_nombre) {
//        this.cupon_nombre = cupon_nombre;
//    }
//}

import com.google.gson.annotations.SerializedName;

public class MisCuponRequest {
    @SerializedName("cupon_id")
    private int cuponId;

    public MisCuponRequest(int cuponId) {
        this.cuponId = cuponId;
    }

    public int getCuponId() {
        return cuponId;
    }
}

