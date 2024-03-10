package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R

@Composable
fun OverViewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        val weekDays = viewModel.day.collectAsState().value
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(weekDays) { currentDay ->
                Box(
                    modifier = modifier
                        .width(120.dp)
                        .height(120.dp)
                        .padding(8.dp)
                        .background(
                            color = colorResource(id = R.color.colorPrimary).copy(alpha = 0.2f)
                        )
                        .border(
                            width = 0.5.dp,
                            shape = RoundedCornerShape(5.dp),
                            color = Color.LightGray
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = modifier.fillMaxSize(),
                        text = currentDay,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun OverViewScreenPreview(modifier: Modifier = Modifier) {
    OverViewScreen(modifier)
}