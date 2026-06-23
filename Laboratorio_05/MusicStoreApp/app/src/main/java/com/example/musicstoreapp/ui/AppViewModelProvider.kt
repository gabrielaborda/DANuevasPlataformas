package com.example.musicstoreapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.musicstoreapp.MusicStoreApplication
import com.example.musicstoreapp.ui.viewmodel.AuthViewModel
import com.example.musicstoreapp.ui.viewmodel.DetailViewModel
import com.example.musicstoreapp.ui.viewmodel.HomeViewModel
import com.example.musicstoreapp.ui.viewmodel.ProfileViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                musicStoreApplication().container.productRepository
            )
        }
        initializer {
            DetailViewModel(
                musicStoreApplication().container.productRepository,
                musicStoreApplication().container.productVisitLogger
            )
        }
        initializer {
            AuthViewModel(
                musicStoreApplication().container.userRepository,
                musicStoreApplication().container.sessionManager
            )
        }
        initializer {
            ProfileViewModel(
                musicStoreApplication().container.sessionManager
            )
        }
    }
}

fun CreationExtras.musicStoreApplication(): MusicStoreApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MusicStoreApplication)