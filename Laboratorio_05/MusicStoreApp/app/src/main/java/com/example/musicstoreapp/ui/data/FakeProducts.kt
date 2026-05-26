package com.example.musicstoreapp.ui.data

import com.example.musicstoreapp.R
import com.example.musicstoreapp.ui.model.Product

val fakeProducts = listOf(
    Product(
        id = 1,
        nombre = "Taylor 214ce",
        precio = 1499.0,
        descripcion = "Guitarra acústica de gran auditorio con un sonido brillante y cuerpo de madera de koa.",
        imagen = R.drawable.guitar
    ),

    Product(
        id = 2,
        nombre = "Fender Stratocaster",
        precio = 1200.0,
        descripcion = "Guitarra eléctrica profesional ideal para rock y blues.",
        imagen = R.drawable.electric
    ),

    Product(
        id = 3,
        nombre = "Yamaha P-45",
        precio = 1800.0,
        descripcion = "Piano digital compacto con excelente sonido.",
        imagen = R.drawable.piano
    ),

    Product(
        id = 4,
        nombre = "Ibanez GSR200",
        precio = 950.0,
        descripcion = "Bajo eléctrico versátil y cómodo.",
        imagen = R.drawable.bass
    )
)