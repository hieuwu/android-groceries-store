package com.hieuwu.groceriesstore.presentation.shop.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.domain.models.ProductModel

@Composable
fun ProductCatalogue(
    modifier: Modifier = Modifier,
    products: List<ProductModel>,
    title: String,
    onAddToCartClick: (ProductModel) -> Unit,
    onNavigateToProductDetails: (ProductModel) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = "Show all", modifier = modifier.clickable {

            })
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { item ->
                ProductItem(
                    modifier = modifier.padding(4.dp),
                    product = item,
                    onAddToCartClick = onAddToCartClick,
                    onNavigateToProductDetails = { onNavigateToProductDetails(item) }
                )
            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ProductCataloguePreview(modifier: Modifier = Modifier) {
    ProductCatalogue(
        modifier = modifier,
        title = "Best seller",
        products = listOf(
            ProductModel(
                id = "fsdfsdds",
                name = "Groeceries 1",
                price = 2.4,
                image = "",
            ),
            ProductModel(
                id = "fsdfsdds",
                name = "Groeceries 2",
                price = 2.6,
                image = "",
            ),
            ProductModel(
                id = "fsdfsdds",
                name = "Groeceries 2",
                price = 2.6,
                image = "",
            ),
            ProductModel(
                id = "fsdfsdds",
                name = "Groeceries 2",
                price = 2.6,
                image = "",
            )
        ),
        onAddToCartClick = {},
        onNavigateToProductDetails = {}
    )
}