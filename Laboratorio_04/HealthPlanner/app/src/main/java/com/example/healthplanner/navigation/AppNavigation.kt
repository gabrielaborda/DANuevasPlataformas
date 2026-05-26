package com.example.healthplanner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.healthplanner.screens.HomeScreen
import com.example.healthplanner.screens.ResultsScreen
import com.example.healthplanner.screens.TaskScreen
import com.example.healthplanner.screens.ToDoScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {

        // 🔹 Screen 1
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        // 🔹 Screen 2
        composable(
            route = Routes.RESULT,
            arguments = listOf(
                navArgument("imc") { type = NavType.FloatType }
            )
        ) { backStackEntry ->

            val imc = backStackEntry.arguments?.getFloat("imc") ?: 0f

            ResultsScreen(
                imc = imc,
                onVolver = { navController.popBackStack() },
                onIrATareas = {
                    navController.navigate("tasks/$imc")
                }
            )
        }

        // 🔹 Screen 3
        composable(
            route = Routes.TASKS,
            arguments = listOf(
                navArgument("imc") { type = NavType.FloatType }
            )
        ) { backStackEntry ->

            val imc = backStackEntry.arguments?.getFloat("imc") ?: 0f

            TaskScreen(
                imc = imc,
                navController = navController
            )
        }

        // 🔹 Screen 4
        composable("todo") {
            ToDoScreen()
        }
    }
}