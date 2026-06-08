package com.example.musicstoreapp.ui.state

data class AuthUiState(

    val loginEmail: String = "",
    val loginPassword: String = "",

    val registerNombre: String = "",
    val registerEmail: String = "",
    val registerPassword: String = "",

    val loginError: String? = null,
    val registerError: String? = null,

    val isLoading: Boolean = false
)