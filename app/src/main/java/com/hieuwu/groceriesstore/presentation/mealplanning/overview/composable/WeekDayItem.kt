package com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.WeekDay

@Composable
fun WeekDayItem(
    modifier: Modifier = Modifier,
    currentDay: WeekDay,
    onItemClick: (Int) -> Unit,
    index: Int,
) {
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
                onItemClick(index)
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