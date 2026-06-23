package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.data.local.SessionManager
import com.example.musicstoreapp.ui.state.ProfileUiState

class ProfileViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    init {
        loadUser()
    }

    private fun loadUser() {

        val user = sessionManager.getSession()

        if (user != null) {

            uiState = uiState.copy(
                nombre = user.nombre,
                email = user.email,
                isLoggedIn = true
            )
        }
    }

    fun logout() {

        sessionManager.clearSession()

        uiState = ProfileUiState()
    }
}