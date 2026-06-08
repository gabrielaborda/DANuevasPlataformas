package com.example.musicstoreapp.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.musicstoreapp.ui.data.local.UserStorage
import com.example.musicstoreapp.ui.state.ProfileUiState

class ProfileViewModel(
    application: Application
) : AndroidViewModel(application) {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    init {
        loadUser()
    }

    private fun loadUser() {

        val user = UserStorage.getSession(getApplication())

        if (user != null) {

            uiState = uiState.copy(
                nombre = user.nombre,
                email = user.email,
                isLoggedIn = true
            )
        }
    }

    fun logout() {

        UserStorage.clearSession(getApplication())

        uiState = ProfileUiState()
    }
}