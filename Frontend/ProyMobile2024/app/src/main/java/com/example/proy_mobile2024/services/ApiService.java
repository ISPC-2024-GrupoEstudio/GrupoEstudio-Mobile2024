package com.example.proy_mobile2024.services;
import com.example.proy_mobile2024.model.Carrito;
import com.example.proy_mobile2024.model.ItemCarritoData;
import com.example.proy_mobile2024.model.Producto;
import com.example.proy_mobile2024.model.TokenResponse;

import java.util.List;

import com.example.proy_mobile2024.model.LoginData;
import com.example.proy_mobile2024.model.Usuario;
import com.example.proy_mobile2024.model.UsuarioPerfil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
//    // 1. Solicitud de inicio de sesión (POST)
//    Call<Void> loginUser(@Body LoginData loginData);

    //2.solicitud de productos para galería (GET)
    @GET("productos")
    Call<List<Producto>> obtenerProductos();
    
    @POST("auth/login/")
    Call<TokenResponse> loginUser(@Body LoginData loginData);

    @POST("auth/register/")
    Call<Usuario> registerUser(@Body Usuario usuario);

    @POST("token/refresh/")
    Call<TokenResponse> refreshToken(@Body TokenRefreshRequest request);

    @GET("usuarios/")
    Call<List<UsuarioPerfil>> getPerfil();

    @POST("add-to-cart/")
    Call<Void> agregarProductoACarrito(@Body ItemCarritoData item);

    @GET("cart/{nombreUsuario}")
    Call<List<Carrito>> obtenerCarrito(@Path("nombreUsuario") String nombreUsuario);

    @DELETE("delete-from-cart/{id}/")
    Call<Void> eliminarDeCarrito(@Path("id") int id_carrito);

}

