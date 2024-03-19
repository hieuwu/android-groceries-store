package com.hieuwu.groceriesstore.presentation.mealplanning.overview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.hieuwu.groceriesstore.R

@Composable
fun LineTextButton(
    modifier: Modifier = Modifier,
    textTitle: String,
    onButtonClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textTitle,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = onButtonClick) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null,
                tint = colorResource(id = R.color.primary_button)
            )
            Text(
                text = "Add",
                color = colorResource(id = R.color.primary_button)
            )
        }
    }
}