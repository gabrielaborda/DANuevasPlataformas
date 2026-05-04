package com.example.navegationcomposeapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = Routes.screenA, builder = {
        composable (Routes.screenA) {
            ScreenA(navController)
        }
        composable(Routes.screenB+"/{nombre}") {
            var nombre = it.arguments?.getString("nombre")
            ScreenB(nombre?:"no nombre")
        }
    })
}

