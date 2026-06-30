package com.example.musicstoreapp.ui.data.repository

import android.content.Context
import com.example.musicstoreapp.ui.data.local.UserEntity
import com.example.musicstoreapp.ui.data.remote.ApiClient
import com.example.musicstoreapp.ui.data.model.LoginRequest
import com.example.musicstoreapp.ui.data.model.RegisterRequest

class UserRepository(private val context: Context) {

    suspend fun register(nombre: String, email: String, password: String): Boolean {
        return try {
            val response = ApiClient.api.register(RegisterRequest(nombre, email, password))
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun login(email: String, password: String): UserEntity? {
        return try {
            val response = ApiClient.api.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    UserEntity(
                        id = loginResponse.id,
                        nombre = loginResponse.nombre,
                        email = loginResponse.email,
                        password = password
                    )
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}