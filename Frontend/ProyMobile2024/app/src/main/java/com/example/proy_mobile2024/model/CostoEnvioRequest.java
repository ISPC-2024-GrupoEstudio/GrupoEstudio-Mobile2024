package com.example.proy_mobile2024.model;

public class CostoEnvioRequest {
    private String cpOrigen;
    private String cpDestino;
    private String provinciaOrigen;
    private String provinciaDestino;
    private String peso;

    // Constructor
    public CostoEnvioRequest(String cpOrigen, String cpDestino, String provinciaOrigen, String provinciaDestino, String peso) {
        this.cpOrigen = cpOrigen;
        this.cpDestino = cpDestino;
        this.provinciaOrigen = provinciaOrigen;
        this.provinciaDestino = provinciaDestino;
        this.peso = peso;
    }

    // Constructor vacío
    public CostoEnvioRequest() {}

    // Getters
    public String getCpOrigen() { return cpOrigen; }
    public String getCpDestino() { return cpDestino; }
    public String getProvinciaOrigen() { return provinciaOrigen; }
    public String getProvinciaDestino() { return provinciaDestino; }
    public String getPeso() { return peso; }

    // Setters
    public void setCpOrigen(String cpOrigen) { this.cpOrigen = cpOrigen; }
    public void setCpDestino(String cpDestino) { this.cpDestino = cpDestino; }
    public void setProvinciaOrigen(String provinciaOrigen) { this.provinciaOrigen = provinciaOrigen; }
    public void setProvinciaDestino(String provinciaDestino) { this.provinciaDestino = provinciaDestino; }
    public void setPeso(String peso) { this.peso = peso; }
}
