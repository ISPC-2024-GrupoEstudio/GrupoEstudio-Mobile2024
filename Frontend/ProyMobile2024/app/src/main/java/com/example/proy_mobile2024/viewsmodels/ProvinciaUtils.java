package com.example.proy_mobile2024.viewsmodels;

import android.os.Build;

import java.util.HashMap;
import java.util.Map;

public class ProvinciaUtils {

    public static String determinarProvinciaDesdeCP(String codigoPostal) {
        int cp = Integer.parseInt(codigoPostal);
        if (cp >= 1000 && cp <= 1999) return "AR-C"; // CABA
        if (cp >= 2000 && cp <= 2999) return "AR-B"; // Buenos Aires
        if (cp >= 3000 && cp <= 3999) return "AR-E"; // Entre Ríos
        if (cp >= 4000 && cp <= 4999) return "AR-T"; // Tucumán (ajustalo si querés Salta o Jujuy)
        if (cp >= 5000 && cp <= 5999) return "AR-X"; // Córdoba
        if (cp >= 6000 && cp <= 6999) return "AR-M"; // Mendoza
        if (cp >= 7000 && cp <= 7999) return "AR-U"; // Chubut
        if (cp >= 8000 && cp <= 8999) return "AR-R"; // Río Negro
        if (cp >= 9000 && cp <= 9999) return "AR-Z"; // Santa Cruz
        return "AR-B"; // Default
    }

    public static String obtenerNombreProvincia(String codigo) {
        Map<String, String> provincias = new HashMap<>();
        provincias.put("AR-C", "Ciudad Autónoma de Buenos Aires");
        provincias.put("AR-B", "Buenos Aires");
        provincias.put("AR-K", "Catamarca");
        provincias.put("AR-H", "Chaco");
        provincias.put("AR-U", "Chubut");
        provincias.put("AR-X", "Córdoba");
        provincias.put("AR-W", "Corrientes");
        provincias.put("AR-E", "Entre Ríos");
        provincias.put("AR-P", "Formosa");
        provincias.put("AR-Y", "Jujuy");
        provincias.put("AR-L", "La Pampa");
        provincias.put("AR-F", "La Rioja");
        provincias.put("AR-M", "Mendoza");
        provincias.put("AR-N", "Misiones");
        provincias.put("AR-Q", "Neuquén");
        provincias.put("AR-R", "Río Negro");
        provincias.put("AR-A", "Salta");
        provincias.put("AR-J", "San Juan");
        provincias.put("AR-D", "San Luis");
        provincias.put("AR-Z", "Santa Cruz");
        provincias.put("AR-S", "Santa Fe");
        provincias.put("AR-G", "Santiago del Estero");
        provincias.put("AR-V", "Tierra del Fuego");
        provincias.put("AR-T", "Tucumán");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return provincias.getOrDefault(codigo, "Provincia no identificada");
        }
        String nombre = provincias.get(codigo);
        return nombre != null ? nombre : "Provincia no identificada";
    }
}
