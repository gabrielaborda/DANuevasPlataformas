package com.example.musicstoreapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicstoreapp.R
import com.example.musicstoreapp.ui.navigation.Routes

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    NavigationBar {

        NavigationBarItem(

            onClick = onHomeClick,
            selected = currentRoute == Routes.HOME,
            icon = {
                Icon(
                    painter = painterResource(id= R.drawable.home_icon),
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = {
                Text("Home")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
        )

        NavigationBarItem(
            onClick = onCartClick,
            selected = currentRoute == Routes.CART,
            icon = {
                Icon(
                    painter = painterResource(id=R.drawable.cart_icon),
                    contentDescription = "Cart",
                    modifier = Modifier.size(30.dp),

                )
            },
            label = {
                Text("Cart")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )

        )

        NavigationBarItem(
            onClick = onProfileClick,
            selected = currentRoute == Routes.PROFILE,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = "Profile",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = {
                Text("Profile")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
        )


    }


}
@Preview(showBackground = true)
@Composable
fun PreviewBottomBar(){
        AppBottomBar(
            currentRoute = "home",
            onHomeClick = {},
            onCartClick = {},
            onProfileClick = {}
        )
}