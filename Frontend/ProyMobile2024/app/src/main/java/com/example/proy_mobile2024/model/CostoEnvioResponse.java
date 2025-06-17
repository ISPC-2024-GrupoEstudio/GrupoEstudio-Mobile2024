package com.example.proy_mobile2024.model;

public class CostoEnvioResponse {
    private PaqarClasico paqarClasico;

    public PaqarClasico getPaqarClasico() {
        return paqarClasico;
    }
    public void setPaqarClasico(PaqarClasico paqarClasico) {
        this.paqarClasico = paqarClasico;
    }

    public static class PaqarClasico {
        private double aSucursal;
        private double aDomicilio;

        public double getASucursal() {
            return aSucursal;
        }
        public void setASucursal(double aSucursal) {
            this.aSucursal = aSucursal;
        }
        public double getADomicilio() {
            return aDomicilio;
        }
        public void setADomicilio(double aDomicilio) {
            this.aDomicilio = aDomicilio;
        }
    }
}
