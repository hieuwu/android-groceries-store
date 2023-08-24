package com.hieuwu.groceriesstore.presentation.cart.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.LineItemModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
    lineItem: LineItemModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 2.dp,
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = lineItem.image,
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
                    .padding(8.dp)
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = lineItem.name ?: "",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = "$ ${lineItem.price}"
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = onDecrease) {
                        Icon(imageVector = Icons.Filled.Remove, contentDescription = null)
                    }
                    Spacer(modifier = modifier.width(4.dp))
                    Text(text = "${lineItem.quantity}")
                    Spacer(modifier = modifier.width(4.dp))
                    IconButton(onClick = onIncrease) {
                        Icon(
                            imageVector = Icons.Filled.Add, contentDescription = null,
                            tint = colorResource(id = R.color.primary_button)
                        )
                    }
                }
            }
            Column(modifier = modifier.padding(8.dp)) {
                Text(
                    text = "$ ${lineItem.subtotal}",
                    style = MaterialTheme.typography.labelLarge
                )
                IconButton(
                    onClick = onRemove
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = colorResource(id = R.color.colorPrimary)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun CartItemPreview() {
    CartItem(
        onDecrease = {},
        onIncrease = {},
        onRemove = {},
        lineItem = LineItemModel(
            id = null,
            name = "Fishing",
            price = 12.3,
            image = "",
            quantity = 2,
            subtotal = 24.23
        )
    )
}