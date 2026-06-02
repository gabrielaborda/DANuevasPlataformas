package com.example.musicstoreapp.ui.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

data class UserEntity(
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val password: String
)

object UserStorage {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USERS = "users"
    private val gson = Gson()

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Obtener todos los usuarios
    fun getUsers(context: Context): MutableList<UserEntity> {
        val json = getPrefs(context).getString(KEY_USERS, null) ?: return mutableListOf()
        val type = object : com.google.gson.reflect.TypeToken<MutableList<UserEntity>>() {}.type
        return gson.fromJson(json, type)
    }

    // Guardar lista de usuarios
    private fun saveUsers(context: Context, users: MutableList<UserEntity>) {
        getPrefs(context).edit().putString(KEY_USERS, gson.toJson(users)).apply()
    }

    // Registrar usuario, retorna false si el email ya existe
    fun register(context: Context, nombre: String, email: String, password: String): Boolean {
        val users = getUsers(context)
        if (users.any { it.email == email }) return false
        val newUser = UserEntity(
            id = users.size + 1,
            nombre = nombre,
            email = email,
            password = password
        )
        users.add(newUser)
        saveUsers(context, users)
        return true
    }

    // Login, retorna el usuario si existe o null
    fun login(context: Context, email: String, password: String): UserEntity? {
        return getUsers(context).find { it.email == email && it.password == password }
    }

    // Guardar sesión activa
    fun saveSession(context: Context, user: UserEntity) {
        getPrefs(context).edit().putString("session", gson.toJson(user)).apply()
    }

    // Obtener sesión activa
    fun getSession(context: Context): UserEntity? {
        val json = getPrefs(context).getString("session", null) ?: return null
        return gson.fromJson(json, UserEntity::class.java)
    }

    // Cerrar sesión
    fun clearSession(context: Context) {
        getPrefs(context).edit().remove("session").apply()
    }
}