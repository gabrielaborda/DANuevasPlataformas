package com.example.healthplanner.utils

fun calcularIMC(peso: Float, altura: Float): Float {
    return peso / ((altura / 100) * (altura / 100))
}
