package com.hieuwu.groceriesstore.presentation.orderhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R

@Composable
fun OrderHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderListViewModel = hiltViewModel()
) {
    Box(modifier = modifier.background(colorResource(id = R.color.colorPrimary))) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            text = "Order history",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(72.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 64.dp)
                .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                .background(Color.White),
        ) {
            val orderList = viewModel.orderList.collectAsState()
            LazyColumn(contentPadding = PaddingValues(20.dp)) {
                items(orderList.value) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = 3.dp
                    ) {
                        Column(modifier = modifier.padding(8.dp)) {
                            Text(text = "Created at " + it.createdAt)
                            Text(text = "Shipped to " + it.address.toString())
                            Card(
                                modifier = modifier.padding(top = 8.dp),
                                backgroundColor = colorResource(id = R.color.colorPrimary),
                            ) {
                                Text(
                                    modifier = modifier.padding(4.dp),
                                    text = "$ ${it.totalPrice}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}