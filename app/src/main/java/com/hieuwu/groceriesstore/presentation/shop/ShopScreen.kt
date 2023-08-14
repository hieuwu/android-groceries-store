package com.hieuwu.groceriesstore.presentation.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.shop.composables.Carousel
import com.hieuwu.groceriesstore.presentation.shop.composables.ProductCatalogue

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = hiltViewModel()
) {
    val products = viewModel.productList.collectAsState()
    Box(modifier = modifier.background(colorResource(id = R.color.colorPrimary))) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                .background(Color.White)
        ) {
            Image(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .size(64.dp),
                painter = painterResource(id = R.drawable.colorful_carrot),
                contentDescription = null,
            )
            Text(text = stringResource(id = R.string.welcome_prompt))
            Carousel(modifier = modifier)
            ProductCatalogue(
                products = products.value,
                title = "Best seller",
                onAddToCartClick = { product -> viewModel.addToCart(product) })
        }

    }

}