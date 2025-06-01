package com.hieuwu.groceriesstore.presentation.checkout.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
fun PaymentMethodSection(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.payment_method),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = stringResource(id = R.string.edit),
            color = colorResource(id = R.color.colorPrimary),
            modifier = Modifier.clickable { onEdit() }
        )
    }
}