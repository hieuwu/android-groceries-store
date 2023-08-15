package com.hieuwu.groceriesstore.presentation.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
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

        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            text = "Groceries Store",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(top = 64.dp)
                .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                .background(Color.White)
        ) {
            Image(
                modifier = modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(48.dp),
                painter = painterResource(id = R.drawable.colorful_carrot),
                contentDescription = null,
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.welcome_prompt)
            )
            Carousel(modifier = modifier.padding(4.dp))
            ProductCatalogue(
                products = products.value,
                title = "Best seller",
                onAddToCartClick = { product -> viewModel.addToCart(product) }
            )
            Spacer(modifier = modifier.height(12.dp))
            ProductCatalogue(
                products = products.value,
                title = "Hot deal",
                onAddToCartClick = { product -> viewModel.addToCart(product) }
            )
            Spacer(modifier = modifier.height(12.dp))
            ProductCatalogue(
                products = products.value,
                title = "Exclusive offer",
                onAddToCartClick = { product -> viewModel.addToCart(product) }
            )
        }
    }

}