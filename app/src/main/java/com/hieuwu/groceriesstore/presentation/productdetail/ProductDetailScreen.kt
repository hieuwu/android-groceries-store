package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val product = viewModel.product.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text("Product details")
                },
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.NavigateBefore,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = product?.image,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product?.name ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "$ ${product?.price}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val quantity = viewModel.quantity.collectAsState().value
                IconButton(onClick = {
                    viewModel.decreaseQty()
                }) {
                    Icon(imageVector = Icons.Filled.Remove, contentDescription = null)
                }
                Spacer(modifier = modifier.width(8.dp))
                Text(text = "$quantity")
                Spacer(modifier = modifier.width(8.dp))
                IconButton(onClick = {
                    viewModel.increaseQty()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add, contentDescription = null,
                        tint = colorResource(id = R.color.primary_button)
                    )
                }
            }
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = product?.description ?: "")
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = "Nutrition",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = product?.nutrition ?: "")
            Spacer(modifier = modifier.height(12.dp))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addToCart()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Added ${product?.name}"
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.primary_button),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Add to Basket")
            }
        }
    }

}

@Preview
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        onNavigateBack = {}
    )
}