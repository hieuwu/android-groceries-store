package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import android.content.ContentResolver
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.mealplanning.addmeal.AddMealBottomSheet
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.EmptyListIndicatorText
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.LineTextButton
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.MealItem
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.SwipeToDeleteContainer
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.WeekDayItem
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.MealType
import java.io.ByteArrayOutputStream
import java.io.InputStream
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverViewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
    onNavigateUpClick: () -> Unit
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
                IconButton(onClick = onNavigateUpClick) {
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
            val contentResolver = LocalContext.current.contentResolver
            AddMealBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                },
                sheetState = sheetState,
                mealAddState = mealAddState,
                onAddMealClick = {
                    val image = uriToByteArray(contentResolver, mealAddState.value.imageUri.value ?: Uri.parse(""))
                    viewModel.onAddMeal(
                        mealType = mealType.value,
                        name = mealAddState.value.name.value,
                        ingredients = mealAddState.value.ingredients.value,
                        mealImageUri = image
                    )
                    mealAddState.value.name.value = ""
                    mealAddState.value.ingredients.value = listOf()
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet.value = false
                        }
                    }
                }
            )
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
                items(breakfastList, key = { it.id }) { meal ->
                    SwipeToDeleteContainer(
                        item = meal,
                        onDelete = {
                            viewModel.onRemoveMeal(meal.id)
                        }
                    ) { meal ->
                        MealItem(meal = meal, modifier = modifier.padding(16.dp))
                    }
                }
            } else {
                item {
                    EmptyListIndicatorText()
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
                items(lunchMeals, key = { it.id }) { meal ->
                    SwipeToDeleteContainer(
                        item = meal,
                        onDelete = {
                            viewModel.onRemoveMeal(meal.id)
                        }
                    ) { meal ->
                        MealItem(meal = meal, modifier = modifier.padding(16.dp))
                    }
                }
            } else {
                item {
                    EmptyListIndicatorText()
                }
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
                items(dinnerMeals, key = { it.id }) { meal ->
                    SwipeToDeleteContainer(
                        item = meal,
                        onDelete = {
                            viewModel.onRemoveMeal(meal.id)
                        }
                    ) { meal ->
                        MealItem(meal = meal, modifier = modifier.padding(16.dp))
                    }
                }
            } else {
                item {
                    EmptyListIndicatorText()
                }
            }
        }
    }
}

data class MealAddingState(
    val name: MutableState<String> = mutableStateOf(""),
    val ingredients: MutableState<List<String>> = mutableStateOf(listOf()),
    val imageUri: MutableState<Uri?> = mutableStateOf(null)
)

@Composable
@Preview
fun OverViewScreenPreview(modifier: Modifier = Modifier) {
    OverViewScreen(modifier, onNavigateUpClick = {})
}



private fun getBytes(inputStream: InputStream): ByteArray {
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)
    var len = 0
    while (inputStream.read(buffer).also { len = it } != -1) {
        byteBuffer.write(buffer, 0, len)
    }
    return byteBuffer.toByteArray()
}


private fun uriToByteArray(contentResolver: ContentResolver, uri: Uri): ByteArray {
    if (uri == Uri.EMPTY) {
        return byteArrayOf()
    }
    val inputStream = contentResolver.openInputStream(uri)
    if (inputStream != null) {
        return getBytes(inputStream)
    }
    return byteArrayOf()
}