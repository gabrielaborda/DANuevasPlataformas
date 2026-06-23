package com.example.musicstoreapp.di

import android.content.Context
import com.example.musicstoreapp.ui.data.analytics.ProductVisitLogger
import com.example.musicstoreapp.ui.data.analytics.ProductVisitLoggerImpl
import com.example.musicstoreapp.ui.data.local.SessionManager
import com.example.musicstoreapp.ui.data.local.SessionManagerImpl
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.data.repository.UserRepository

interface AppContainer {
    val productRepository: ProductRepository
    val userRepository: UserRepository
    val productVisitLogger: ProductVisitLogger
    val sessionManager: SessionManager
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val productRepository: ProductRepository by lazy {
        ProductRepository()
    }

    override val userRepository: UserRepository by lazy {
        UserRepository(context)
    }

    override val sessionManager: SessionManager by lazy {
        SessionManagerImpl(context)
    }

    override val productVisitLogger: ProductVisitLogger by lazy {
        ProductVisitLoggerImpl(sessionManager)
    }
}