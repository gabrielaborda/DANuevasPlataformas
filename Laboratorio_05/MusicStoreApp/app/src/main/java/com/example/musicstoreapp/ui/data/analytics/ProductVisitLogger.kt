package com.example.musicstoreapp.ui.data.analytics

import android.util.Log
import com.example.musicstoreapp.ui.data.local.SessionManager
import com.example.musicstoreapp.ui.data.model.Product

interface ProductVisitLogger {
    fun logVisit(product: Product)
}

class ProductVisitLoggerImpl(private val sessionManager: SessionManager) : ProductVisitLogger {
    override fun logVisit(product: Product) {
        val user = sessionManager.getSession()
        val userName = user?.nombre ?: "Invitado"
        Log.d("ProductVisitLogger", "Usuario: $userName visitó el producto: ${product.nombre} (ID: ${product.id})")
    }
}