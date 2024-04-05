package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.authentication.composables.IconTextInput
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.IngredientChip
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.LineTextButton
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.MealItem
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.WeekDayItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverViewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel()
) {
    val breakfastList = viewModel.breakfastMeals.collectAsState().value
    val weekDays = viewModel.day.collectAsState().value
    val lunchMeals = viewModel.lunchMeals.collectAsState().value
    val dinnerMeals = viewModel.dinnerMeals.collectAsState().value
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val mealType = remember { mutableStateOf(MealType.BREAKFAST) }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Today's meals",
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.colorPrimary)
            ),
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.NavigateBefore,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
    }
    ) { contentPadding ->
        val scope = rememberCoroutineScope()
        val mealAddState = remember { mutableStateOf(MealAddingState()) }
        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(350.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                ) {
                    Text(
                        text = "Add a meal",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    IconTextInput(
                        leadingIcon = Icons.Default.Cookie,
                        trailingIcon = Icons.Rounded.Backspace,
                        value = mealAddState.value.name.value,
                        placeholder = "Name of the meal",
                        onValueChange = {
                            mealAddState.value.name.value = it
                        },
                        onTrailingIconClick = {
                            mealAddState.value.name.value = ""
                        }
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(3),
                        verticalItemSpacing = 4.dp,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            items(mealAddState.value.ingredients.value) { photo ->
                                IngredientChip(text = photo, onDismiss = {
                                    val newList = mutableListOf<String>().apply {
                                        addAll(mealAddState.value.ingredients.value)
                                        remove(photo)
                                    }
                                    mealAddState.value.ingredients.value = newList
                                })
                            }
                        },
                    )
                    val newIngredient = remember { mutableStateOf("") }
                    IconTextInput(
                        leadingIcon = Icons.Default.Cookie,
                        trailingIcon = Icons.Rounded.AddCircle,
                        value = newIngredient.value,
                        placeholder = "Ingredients",
                        onValueChange = {
                            newIngredient.value = it
                        },
                        onTrailingIconClick = {
                            val newList = mutableListOf<String>().apply {
                                addAll(mealAddState.value.ingredients.value)
                                add(newIngredient.value)
                            }
                            mealAddState.value.ingredients.value = newList
                            newIngredient.value = ""
                        }
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.onAddMeal(
                                mealType = mealType.value,
                                name = mealAddState.value.name.value,
                                ingredients = mealAddState.value.ingredients.value
                            )
                            mealAddState.value.name.value = ""
                            mealAddState.value.ingredients.value = listOf()
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet.value = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimary)),
                    ) {
                        androidx.compose.material3.Text("Add this meal")
                    }
                }
            }
        }
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    onButtonClick = {
                        mealType.value = MealType.BREAKFAST
                        showBottomSheet.value = true
                    }
                )
            }
            if (breakfastList.isNotEmpty()) {
                items(breakfastList) { meal ->
                    MealItem(meal = meal)
                }
            } else {
                item {
                    Text(
                        text = "There is no meal. Add a meal to your plan",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {
                LineTextButton(
                    modifier = modifier,
                    textTitle = "Lunch",
                    onButtonClick = {
                        mealType.value = MealType.LUNCH
                        showBottomSheet.value = true
                    }
                )
            }

            if (lunchMeals.isNotEmpty()) {
                items(lunchMeals) { meal ->
                    MealItem(meal = meal, modifier = modifier)
                }
            } else {
                item {
                    Text(
                        text = "There is no meal. Add a meal to your plan",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(lunchMeals) { meal ->
                MealItem(meal = meal, modifier = modifier)
            }

            item {
                LineTextButton(
                    modifier = modifier,
                    textTitle = "Dinner",
                    onButtonClick = {
                        mealType.value = MealType.DINNER
                        showBottomSheet.value = true
                    }
                )
            }

            if (dinnerMeals.isNotEmpty()) {
                items(dinnerMeals) { meal ->
                    MealItem(meal = meal, modifier = modifier)
                }
            } else {
                item {
                    Text(
                        text = "There is no meal. Add a meal to your plan",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}

private data class MealAddingState(
    val name: MutableState<String> = mutableStateOf(""),
    val ingredients: MutableState<List<String>> = mutableStateOf(listOf())
)

@Composable
@Preview
fun OverViewScreenPreview(modifier: Modifier = Modifier) {
    OverViewScreen(modifier)
}