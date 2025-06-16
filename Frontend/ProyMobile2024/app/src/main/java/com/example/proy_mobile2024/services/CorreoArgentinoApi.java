package com.example.proy_mobile2024.services;

import com.example.proy_mobile2024.model.CostoEnvioRequest;
import com.example.proy_mobile2024.model.CostoEnvioResponse;
import com.example.proy_mobile2024.model.PesoVolumetricoRequest;
import com.example.proy_mobile2024.model.PesoVolumetricoResponse;
import com.example.proy_mobile2024.model.Sucursal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CorreoArgentinoApi {

    @Headers({
            "X-RapidAPI-Key: 803b62e838mshb358622f22ad8e2p10f250jsn6f64206459b9",
            "X-RapidAPI-Host: correo-argentino1.p.rapidapi.com",
            "Content-Type: application/json"
    })
    @POST("calcularPrecio")
    Call<CostoEnvioResponse> calcularCostoEnvio(@Body CostoEnvioRequest request);

    @Headers({
            "X-RapidAPI-Key: 803b62e838mshb358622f22ad8e2p10f250jsn6f64206459b9",
            "X-RapidAPI-Host: correo-argentino1.p.rapidapi.com"
    })
    @GET("obtenerSucursales")
    Call<List<Sucursal>> obtenerSucursales(@Query("provincia") String provincia);
}
