package com.hieuwu.groceriesstore.presentation.shop.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.ProductModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductModel,
    onAddToCartClick: (ProductModel) -> Unit,
    onNavigateToProductDetails: (String) -> Unit
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .height(260.dp),
        elevation = 2.dp,
        onClick = { onNavigateToProductDetails(product.id) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
        ) {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = product.image,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
            Text(
                modifier = modifier.fillMaxWidth(), text = product.name ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = product.description ?: "",
                maxLines = 2,
            )
            Spacer(modifier = modifier.height(4.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${product.price}$",
                    style = MaterialTheme.typography.h6
                )
                Button(
                    onClick = { onAddToCartClick(product) },
                    modifier = Modifier.size(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.colorPrimary)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true, showBackground = false
)
@Composable
private fun ProductItemPreview(modifier: Modifier = Modifier) {
    ProductItem(
        modifier = modifier,
        product = ProductModel(
            id = "fsdfsdds",
            name = "Groeceries",
            price = 2.4,
            image = "",
        ),
        onAddToCartClick = {},
        onNavigateToProductDetails = {}
    )
}