package com.example.proy_mobile2024.model;

public class Credentials {
    private String idToken; // Cambia esto si tu token tiene un nombre diferente

    // Constructor
    public Credentials(String idToken) {
        this.idToken = idToken;
    }

    // Getter
    public String getIdToken() {
        return idToken;
    }

    // Setter
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
