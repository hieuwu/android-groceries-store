package com.hieuwu.groceriesstore.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.cart.composables.CartItem

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val lineItems = viewModel.order.collectAsState().value?.lineItemList?.toList() ?: listOf()
        val total = viewModel.order.collectAsState().value?.total

        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "My basket", style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.colorPrimary)
            )
            Text(
                text = "$ $total",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.colorPrimary)
            )

        }
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            items(lineItems) { item ->
                CartItem(
                    onIncrease = { viewModel.increaseQty(item) },
                    onDecrease = { viewModel.decreaseQty(item) },
                    onRemove = { /*TODO*/ },
                    lineItem = item
                )
            }
        }
    }

}
