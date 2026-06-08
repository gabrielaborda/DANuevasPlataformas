package com.example.musicstoreapp.ui.data.repository

import com.example.musicstoreapp.ui.data.fakeProducts
import com.example.musicstoreapp.ui.data.model.Product

class ProductRepository {
    fun getProducts(): List<Product> {
        return fakeProducts;
    }
}