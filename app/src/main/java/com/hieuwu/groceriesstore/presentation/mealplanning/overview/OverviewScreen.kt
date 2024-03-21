package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.LineTextButton
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.MealItem
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.WeekDayItem

@Composable
fun OverViewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel()
) {
    val breakfastList = viewModel.breakfastMeals.collectAsState().value
    val weekDays = viewModel.day.collectAsState().value
    val lunchMeals = viewModel.lunchMeals.collectAsState().value
    val dinnerMeals = viewModel.dinnerMeals.collectAsState().value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxWidth()
                    .height(64.dp),
                columns = GridCells.Fixed(7)
            ) {
                itemsIndexed(weekDays) { index, currentDay ->
                    WeekDayItem(
                        modifier = modifier,
                        currentDay = currentDay,
                        onItemClick = { viewModel.onWeekDaySelected(index) },
                        index = index
                    )
                }
            }
        }
        item {
            LineTextButton(
                modifier = modifier,
                textTitle = "Breakfast",
                onButtonClick = { viewModel.onAddBreakfast() }
            )
        }
        items(breakfastList) { meal ->
            MealItem(meal = meal, modifier = modifier)
        }

        item {
            LineTextButton(
                modifier = modifier,
                textTitle = "Lunch",
                onButtonClick = { viewModel.onAddLunch() }
            )
        }

        items(lunchMeals) { meal ->
            MealItem(meal = meal, modifier = modifier)
        }

        item {
            LineTextButton(
                modifier = modifier,
                textTitle = "Dinner",
                onButtonClick = { viewModel.onAddDinner() }
            )
        }
        items(dinnerMeals) { meal ->
            MealItem(meal = meal, modifier = modifier)
        }

    }
}


@Composable
@Preview
fun OverViewScreenPreview(modifier: Modifier = Modifier) {
    OverViewScreen(modifier)
}