package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.components.AppBottomBar
import com.example.musicstoreapp.ui.components.AppTopBar

@Composable
fun ProfileScreen(
    navController: NavController
) {

    Scaffold(

        topBar = {
            AppTopBar(
                title = "Perfil",
                onCartClick = {
                    navController.navigate("cart")
                }
            )
        },

        bottomBar = {
            AppBottomBar(

                onHomeClick = {
                    navController.navigate("home")
                },

                onCartClick = {
                    navController.navigate("cart")
                },

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

            //  Avatar simple
            Card(
                modifier = Modifier.size(120.dp)
            ) {
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

            //  Nombre
            Text(
                text = "Gabriela Borda",
                style = MaterialTheme.typography.headlineSmall
            )

            //  Email
            Text(
                text = "borda@example.com",
                style = MaterialTheme.typography.bodyLarge
            )

            Divider()

            //  Opciones
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
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Configuración")
            }
        }
    }
}