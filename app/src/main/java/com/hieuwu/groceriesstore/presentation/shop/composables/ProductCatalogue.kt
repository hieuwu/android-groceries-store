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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.domain.models.ProductModel

@Composable
fun ProductCatalogue(
    modifier: Modifier,
    products: List<ProductModel>,
    title: String
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title)
            Text(text = "Show all", modifier = modifier.clickable {

            })
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { item ->
                ProductItem(product = item)
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
        )
    )
}