package com.hieuwu.groceriesstore.presentation.cart

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.cart.composables.CartItem

@OptIn(ExperimentalMaterialApi::class)
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
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.removeItem(item)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = state,
                    background = {
                        val color by animateColorAsState(
                            targetValue = when (state.dismissDirection) {
                                DismissDirection.StartToEnd -> colorResource(id = R.color.colorPrimary)
                                DismissDirection.EndToStart -> colorResource(id = R.color.colorPrimary).copy(
                                    alpha = 0.2f
                                )
                                null -> Color.Transparent
                            }
                        )
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                                .background(color = color)
                                .padding(10.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,
                                tint = colorResource(id = R.color.colorPrimary),
                            )
                        }

                    },
                    dismissContent = {
                        CartItem(
                            onIncrease = { viewModel.increaseQty(item) },
                            onDecrease = { viewModel.decreaseQty(item) },
                            onRemove = { viewModel.removeItem(item) },
                            lineItem = item
                        )
                    },
                    directions = setOf(DismissDirection.EndToStart),
                )
            }
        }
    }

}
