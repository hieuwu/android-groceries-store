package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.LineTextButton

@Composable
fun OverViewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val weekDays = viewModel.day.collectAsState().value
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7)
        ) {
            itemsIndexed(weekDays) { index, currentDay ->
                Box(
                    modifier = modifier
                        .height(64.dp)
                        .padding(8.dp)

                        .clip(
                            shape = RoundedCornerShape(6.dp),
                        )
                        .background(
                            color = if (currentDay.isSelected.value)
                                colorResource(id = R.color.colorPrimary)
                            else Color.Transparent
                        )
                        .clickable {
                            viewModel.onWeekDaySelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        text = currentDay.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black
                    )
                }
            }
        }
        LineTextButton(
            modifier = modifier,
            textTitle = "Breakfast",
            onButtonClick = { viewModel.onAddBreakfast() }
        )
        LineTextButton(
            modifier = modifier,
            textTitle = "Lunch",
            onButtonClick = { viewModel.onAddLunch() }
        )
        LineTextButton(
            modifier = modifier,
            textTitle = "Dinner",
            onButtonClick = { viewModel.onAddDinner() }
        )
    }
}

@Composable
@Preview
fun OverViewScreenPreview(modifier: Modifier = Modifier) {
    OverViewScreen(modifier)
}