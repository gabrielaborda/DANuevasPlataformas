package com.example.imcapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.compose.ui.Modifier
import com.example.imcapp.screens.home.HomeScreen
import com.example.imcapp.screens.result.ResultadosScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {

        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(
            route = Routes.RESULT,
            arguments = listOf(navArgument("imc") {
                type = NavType.FloatType
            })
        ) { backStackEntry ->

            val imc = backStackEntry.arguments?.getFloat("imc") ?: 0f

            ResultadosScreen(
                imc = imc,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}