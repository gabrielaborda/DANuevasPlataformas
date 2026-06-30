package com.example.musicstoreapp.ui.data.remote

import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.model.LoginRequest
import com.example.musicstoreapp.ui.data.model.LoginResponse
import com.example.musicstoreapp.ui.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("products/")
    suspend fun getProducts(): List<Product>

    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("users/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>
}