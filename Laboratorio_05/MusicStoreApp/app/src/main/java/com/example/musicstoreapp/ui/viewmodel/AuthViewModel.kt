package com.example.musicstoreapp.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicstoreapp.ui.data.local.UserEntity
import com.example.musicstoreapp.ui.data.local.UserStorage
import com.example.musicstoreapp.ui.data.repository.UserRepository
import com.example.musicstoreapp.ui.state.AuthUiState
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = UserRepository(application.applicationContext)

    var uiState by mutableStateOf(AuthUiState())
        private set

    fun updateLoginEmail(value: String) {
        uiState = uiState.copy(loginEmail = value)
    }

    fun updateLoginPassword(value: String) {
        uiState = uiState.copy(loginPassword = value)
    }

    fun updateRegisterNombre(value: String) {
        uiState = uiState.copy(registerNombre = value)
    }

    fun updateRegisterEmail(value: String) {
        uiState = uiState.copy(registerEmail = value)
    }

    fun updateRegisterPassword(value: String) {
        uiState = uiState.copy(registerPassword = value)
    }

    fun login(onSuccess: (UserEntity) -> Unit) {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                loginError = null
            )

            val user = repository.login(
                uiState.loginEmail,
                uiState.loginPassword
            )

            if (user != null) {

                UserStorage.saveSession(
                    getApplication(),
                    user
                )

                onSuccess(user)

            } else {

                uiState = uiState.copy(
                    loginError = "Email o contraseña incorrectos"
                )
            }

            uiState = uiState.copy(
                isLoading = false
            )
        }
    }

    fun register(onSuccess: () -> Unit) {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                registerError = null
            )

            if (
                uiState.registerNombre.isBlank() ||
                uiState.registerEmail.isBlank() ||
                uiState.registerPassword.isBlank()
            ) {

                uiState = uiState.copy(
                    registerError = "Todos los campos son obligatorios",
                    isLoading = false
                )

                return@launch
            }

            val exito = repository.register(
                uiState.registerNombre,
                uiState.registerEmail,
                uiState.registerPassword
            )

            if (exito) {

                onSuccess()

            } else {

                uiState = uiState.copy(
                    registerError = "El email ya está registrado"
                )
            }

            uiState = uiState.copy(
                isLoading = false
            )
        }
    }
}