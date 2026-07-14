package com.example.musicstoreapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.musicstoreapp.ui.screens.CartScreen
import com.example.musicstoreapp.ui.screens.HomeScreen
import com.example.musicstoreapp.ui.screens.LoginScreen
import com.example.musicstoreapp.ui.screens.ProductDetailScreen
import com.example.musicstoreapp.ui.screens.ProfileScreen
import com.example.musicstoreapp.ui.screens.RegisterScreen
import com.example.musicstoreapp.ui.services.NotificationManager

@Composable
fun AppNavigation(
    notificationType: String? = null,
    notificationId: String? = null
) {
    val navController = rememberNavController()

    // Lógica de redirección al recibir una notificación
    LaunchedEffect(notificationType, notificationId) {
        if (notificationType != null) {
            when (notificationType) {
                NotificationManager.TYPE_NEW_PRODUCT, 
                NotificationManager.TYPE_OFFER -> {
                    notificationId?.let { id ->
                        navController.navigate("detail/$id")
                    }
                }
                NotificationManager.TYPE_ORDER_UPDATE -> {
                    navController.navigate(Routes.PROFILE)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        // Login
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        // Register
        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        //  Home
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        //  Detail
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productId =
                backStackEntry.arguments?.getInt("productId") ?: 0

            ProductDetailScreen(
                productId = productId,
                navController = navController
            )
        }

        //  Cart
        composable(Routes.CART) {
            CartScreen(navController)
        }

        //  Profile
        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }
    }
}