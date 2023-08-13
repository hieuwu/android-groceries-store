package com.hieuwu.groceriesstore.presentation.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.presentation.shop.composables.Carousel
import com.hieuwu.groceriesstore.presentation.shop.composables.ProductCatalogue

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = hiltViewModel()
) {
    val products = viewModel.productList.collectAsState()
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Carousel(modifier = modifier)
        ProductCatalogue(modifier = modifier,
            products = products.value,
            title = "Best seller",
            onAddToCartClick = { product -> viewModel.addToCart(product) })
    }
}