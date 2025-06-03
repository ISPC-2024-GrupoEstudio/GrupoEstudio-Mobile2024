package com.example.proy_mobile2024;

public class Cupon {
    private String discount;
    private String venue;
    private int[] gradientColors; // Array de colores para el gradiente
    private int leftColor; // Color sólido para la parte izquierda

    public Cupon(String discount, String venue, int[] gradientColors, int leftColor) {
        this.discount = discount;
        this.venue = venue;
        this.gradientColors = gradientColors;
        this.leftColor = leftColor;
    }

    // Getters
    public String getDiscount() { return discount; }
    public String getVenue() { return venue; }
    public int[] getGradientColors() { return gradientColors; }
    public int getLeftColor() { return leftColor; }

    // Setters
    public void setDiscount(String discount) { this.discount = discount; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setGradientColors(int[] gradientColors) { this.gradientColors = gradientColors; }
    public void setLeftColor(int leftColor) { this.leftColor = leftColor; }
}
