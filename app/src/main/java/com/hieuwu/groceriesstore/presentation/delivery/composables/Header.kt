package com.hieuwu.groceriesstore.presentation.delivery.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}