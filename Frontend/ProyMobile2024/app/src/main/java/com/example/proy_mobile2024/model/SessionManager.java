package com.example.proy_mobile2024.model;

public class SessionManager {
    private static SessionManager instance;
    private String jwtToken;
    private boolean isLoggedIn; // Bandera para saber si el usuario tiene sesión activa

    // Constructor privado para Singleton
    private SessionManager() {
        this.isLoggedIn = false; // Por defecto, no está logueado
    }

    // Método para obtener la instancia única de SessionManager
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Guarda el token JWT y marca la sesión como activa
    public void saveToken(String token) {
        this.jwtToken = token;
        this.isLoggedIn = true; // Marca que el usuario está logueado
    }

    // Obtén el token JWT almacenado
    public String getToken() {
        return jwtToken;
    }

    // Verifica si el usuario está logueado
    public boolean isLoggedIn() {
        return this.isLoggedIn; // Devuelve el estado de sesión activa
    }

    // Cierra sesión
    public void logout() {
        this.jwtToken = null;
        this.isLoggedIn = false; // Marca que el usuario ya no está logueado
    }
}


