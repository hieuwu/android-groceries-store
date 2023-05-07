package com.hieuwu.groceriesstore.presentation.orderhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OrderHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderListViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(mutableListOf<String>()) { it ->


            }
        }
    }

}