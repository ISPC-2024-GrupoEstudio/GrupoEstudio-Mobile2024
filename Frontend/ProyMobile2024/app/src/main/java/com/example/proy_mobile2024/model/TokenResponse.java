package com.example.proy_mobile2024.model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    private Usuario usuario;
    private String token;

    @SerializedName("refresh_token")
    private String refresh;

    public TokenResponse(Usuario usuario, String token, String refresh)  {
        this.usuario = usuario;
        this.token = token;
        this.refresh = refresh;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getRefreshToken() {
        return refresh;
    }

    public void setRefreshToken(String refresh) { this.refresh = refresh; }
}
