package com.example.musicstoreapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicstoreapp.R
import com.example.musicstoreapp.ui.data.fakeProducts
import com.example.musicstoreapp.ui.model.Product

@Composable
fun CartItem(
    product: Product,
    onRemove: () -> Unit
) {

    Card {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Image(
                painter = painterResource(id = product.imagen),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = product.nombre,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$ ${product.precio}"
                )
            }
            IconButton(
                onClick = onRemove
            ){
                Icon(
                    painter = painterResource(id = R.drawable.trash_icon),
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(24.dp),
                )

            }
        }
    }
}
