package com.example.proy_mobile2024.model;

public class LoginResponse {
    private String token; // Asumiendo que tu API devuelve un campo llamado "token"

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }
}