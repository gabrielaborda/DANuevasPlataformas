package com.example.musicstoreapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicstoreapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onCartClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title)

        },

        actions = {
            IconButton(
                onClick = onCartClick,

            ){
                Icon(
                    painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "cart",
                    modifier = Modifier.size(25.dp),
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}