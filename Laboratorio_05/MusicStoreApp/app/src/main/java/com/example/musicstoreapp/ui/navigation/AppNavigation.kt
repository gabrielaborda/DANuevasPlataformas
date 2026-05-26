package com.example.musicstoreapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.musicstoreapp.ui.screens.CartScreen
import com.example.musicstoreapp.ui.screens.HomeScreen
import com.example.musicstoreapp.ui.screens.ProductDetailScreen
import com.example.musicstoreapp.ui.screens.ProfileScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

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