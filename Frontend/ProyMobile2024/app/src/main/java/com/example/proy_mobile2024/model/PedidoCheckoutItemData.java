package com.example.proy_mobile2024.model;

public class PedidoCheckoutItemData {
    private double unit_price;
    private String title;
    private int quantity;

    public PedidoCheckoutItemData(double unit_price, String title, int quantity) {
        this.unit_price = unit_price;
        this.title = title;
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}