package com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.presentation.core.widgets.WebImage
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.Meal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    meal: Meal
) {
    Card(
        modifier = modifier
            .height(164.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.medium,
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxSize()
                .fillMaxHeight()
        ) {
            item {
                Row {
                    WebImage(
                        model = meal.imageUrl,
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier
                            .size(124.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
            item {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    stickyHeader {
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(12.dp),
                            text = meal.name
                        )
                    }
                    items(meal.ingredients) { item ->
                        IngredientChip(
                            text = item,
                            onDismiss = {},
                            modifier = modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}