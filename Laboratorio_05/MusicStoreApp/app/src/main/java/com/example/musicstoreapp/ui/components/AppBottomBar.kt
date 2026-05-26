package com.example.musicstoreapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppBottomBar(
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = {
                Text("🏠")
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onCartClick,
            icon = {
                Text("🛒")
            },
            label = {
                Text("Cart")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = {
                Text("👤")
            },
            label = {
                Text("Profile")
            }
        )
    }
}