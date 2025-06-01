package com.hieuwu.groceriesstore.presentation.checkout.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieuwu.groceriesstore.R

@Composable
fun DeliverySection(
    address: String,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit
) {
    Column {
        Row(
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.delivery),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = stringResource(id = R.string.edit),
                color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier.clickable { onEdit() }
            )
        }

        Text(
            text = address,
            fontSize = 16.sp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
    }
}