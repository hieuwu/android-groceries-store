package com.hieuwu.groceriesstore.presentation.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun ProductItem(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = modifier) {
            Image(
                painterResource(R.drawable.car_ui_app_styled_view_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier.width(120.dp)
            )
            Text(text = "product name")
            Text(text = "product description")
            Row(modifier = modifier) {
                Text(text = "23.4$")
                Button(
                    onClick = { /*TODO*/ },
                    modifier = modifier.size(48.dp)
                ) {

                }

            }
        }

    }

}