package com.example.musicstoreapp.ui.data.repository

import android.content.Context
import com.example.musicstoreapp.ui.data.local.UserEntity
import com.example.musicstoreapp.ui.data.local.UserStorage

class UserRepository(private val context: Context) {

    fun register(nombre: String, email: String, password: String): Boolean {
        return UserStorage.register(context, nombre, email, password)
    }

    fun login(email: String, password: String): UserEntity? {
        return UserStorage.login(context, email, password)
    }

}