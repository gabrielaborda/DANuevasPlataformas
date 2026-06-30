package com.example.musicstoreapp.ui.data.repository

import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.remote.ApiClient

class ProductRepository {

    suspend fun getProducts(): List<Product> {
        return ApiClient.api.getProducts()
    }

}