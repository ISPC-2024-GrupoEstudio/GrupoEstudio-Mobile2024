package com.example.proy_mobile2024.model;

import java.util.List;

public class PedidoCheckoutData{
    private String external_reference;
    private List<PedidoCheckoutItemData> items;
    private String from = "app";
    private double montoFinal;

    public String getExternal_reference() {
        return external_reference;
    }

    public void setExternal_reference(String external_reference) {
        this.external_reference = external_reference;
    }

    public List<PedidoCheckoutItemData> getItems() {
        return items;
    }

    public void setItems(List<PedidoCheckoutItemData> items) {
        this.items = items;
    }

    public void setItemsCarrito(List<Carrito> items) {
        List<PedidoCheckoutItemData> itemsData = new java.util.ArrayList<>();
        for (Carrito item : items) {
           PedidoCheckoutItemData itemData = new PedidoCheckoutItemData(item.getProducto().getPrecio(), item.getProducto().getNombre(), item.getCantidad());
           itemsData.add(itemData);
        }
        this.items = itemsData;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }
}
