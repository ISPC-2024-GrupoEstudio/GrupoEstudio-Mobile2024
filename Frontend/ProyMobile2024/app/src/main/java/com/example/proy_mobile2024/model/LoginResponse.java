package com.example.proy_mobile2024.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String access;
    @SerializedName("refresh_token")
    private String refresh;


    public LoginResponse(String access, String refresh)  {
        this.access = access;
        this.refresh = refresh;
    }

    public Usuario getUsuario() {
        return new Usuario("Prueba","apellido",1,12345678,"prueba","prueba","prueba");
    }

    public void setUsuario(Usuario usuario) {  }

    public String getToken() { return access; }

    public void setToken(String token) { this.access = token; }

    public String getRefreshToken() {
        return refresh;
    }

    public void setRefreshToken(String refresh) { this.refresh = refresh; }
}
