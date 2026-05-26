package com.example.musicstoreapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onCartClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title)
        },

        actions = {

            TextButton(
                onClick = onCartClick
            ) {
                Text("🛒")
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        )
    )
}