package com.example.musicstoreapp.ui.data.repository

import com.example.musicstoreapp.ui.data.fakeProducts
import com.example.musicstoreapp.ui.model.Product

class ProductRepository {
    fun getProducts(): List<Product> {
        return fakeProducts;
    }
}