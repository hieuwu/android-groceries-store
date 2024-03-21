package com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.presentation.core.widgets.WebImage
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    meal: Meal
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            WebImage(
                model = meal.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = modifier.size(124.dp)
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = meal.name
            )
        }
    }
}