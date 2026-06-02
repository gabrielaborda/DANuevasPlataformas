package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.components.AppBottomBar
import com.example.musicstoreapp.ui.components.AppTopBar
import com.example.musicstoreapp.ui.data.local.UserStorage
import com.example.musicstoreapp.ui.navigation.Routes

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val user = remember { UserStorage.getSession(context) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Perfil",
                onCartClick = { navController.navigate("cart") }
            )
        },
        bottomBar = {
            AppBottomBar(
                currentRoute = "profile",
                onHomeClick = { navController.navigate("home") },
                onCartClick = { navController.navigate("cart") },
                onProfileClick = { }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(modifier = Modifier.size(120.dp)) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🎵",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }

            Text(
                text = user?.nombre ?: "Sin nombre",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = user?.email ?: "Sin email",
                style = MaterialTheme.typography.bodyLarge
            )

            HorizontalDivider()

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mis pedidos")
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Favoritos")
            }

            OutlinedButton(
                onClick = {
                    UserStorage.clearSession(context)
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}