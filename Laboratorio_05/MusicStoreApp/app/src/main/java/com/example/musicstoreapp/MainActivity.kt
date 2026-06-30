package com.example.musicstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicstoreapp.ui.navigation.AppNavigation
import com.example.musicstoreapp.ui.theme.MusicStoreAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MusicStoreAppTheme {

                Scaffold( modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppNavigation()
                }
            }
        }
    }
}