@file:OptIn(ExperimentalMaterial3Api::class)

package com.hieuwu.groceriesstore.presentation.mealplanning.addmeal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.authentication.composables.IconTextInput
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.MealAddingState
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable.IngredientChip

@Composable
fun AddMealBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    mealAddState: MutableState<MealAddingState>,
    onDismissRequest: () -> Unit,
    onAddMealClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
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
                onClick = onAddMealClick,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimary)),
            ) {
                Text("Add this meal")
            }
        }
    }
}