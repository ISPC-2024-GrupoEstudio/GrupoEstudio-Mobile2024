package com.example.proy_mobile2024.model;

import com.google.gson.annotations.SerializedName;

public class Pedido {
    @SerializedName("id_pedido")
    private int idPedido;

    private String fecha;

    @SerializedName("domicilio_envio")
    private String domicilioEnvio;

    @SerializedName("id_estado_pedido")
    private int idEstadoPedido;

    @SerializedName("nombre_usuario")
    private String nombreUsuario;

    public int getIdPedido() {
        return idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDomicilioEnvio() {
        return domicilioEnvio;
    }

    public int getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public static class UsuarioSimple {
        @SerializedName("nombre_usuario")
        private String nombreUsuario;

        public String getNombreUsuario() {
            return nombreUsuario;
        }
    }
}
