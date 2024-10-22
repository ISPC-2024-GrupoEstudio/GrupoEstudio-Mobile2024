package com.example.proy_mobile2024;

import com.example.proy_mobile2024.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

    // Lista estática que almacenará los productos del carrito
    private static List<Producto> productosCarrito = new ArrayList<>();

    // Método para agregar un producto al carrito
    public static void agregarProducto(Producto producto) {
        productosCarrito.add(producto);
    }

    // Método para obtener todos los productos del carrito
    public static List<Producto> obtenerProductos() {
        return productosCarrito;
    }

    // Método opcional para limpiar el carrito
    public static void limpiarCarrito() {
        productosCarrito.clear();
    }
}
