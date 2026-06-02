package com.example.musicstoreapp.ui.views

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicstoreapp.ui.data.local.UserEntity
import com.example.musicstoreapp.ui.data.local.UserStorage
import com.example.musicstoreapp.ui.data.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application.applicationContext)

    // Estados
    var loginEmail by mutableStateOf("")
    var loginPassword by mutableStateOf("")
    var registerNombre by mutableStateOf("")
    var registerEmail by mutableStateOf("")
    var registerPassword by mutableStateOf("")

    // Resultados
    var loginError by mutableStateOf<String?>(null)
    var registerError by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    fun login(onSuccess: (UserEntity) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            loginError = null

            val user = repository.login(loginEmail, loginPassword)

            if (user != null) {
                UserStorage.saveSession(getApplication(), user)
                onSuccess(user)
            } else {
                loginError = "Email o contraseña incorrectos"
            }

            isLoading = false
        }
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            registerError = null

            if (registerNombre.isBlank() || registerEmail.isBlank() || registerPassword.isBlank()) {
                registerError = "Todos los campos son obligatorios"
                isLoading = false
                return@launch
            }

            val exito = repository.register(registerNombre, registerEmail, registerPassword)

            if (exito) {
                onSuccess()
            } else {
                registerError = "El email ya está registrado"
            }

            isLoading = false
        }
    }
}