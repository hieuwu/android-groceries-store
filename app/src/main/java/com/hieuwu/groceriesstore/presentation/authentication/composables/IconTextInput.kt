package com.hieuwu.groceriesstore.presentation.authentication.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun IconTextInput(
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.colorPrimary),

                    ),
                shape = RoundedCornerShape(6.dp)
            ),
        value = value,
        onValueChange = { text -> onValueChange(text) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = colorResource(id = R.color.primary_button)
            )
        },
        trailingIcon = {
            Icon(
                modifier = modifier.clickable {
                    onTrailingIconClick()
                },
                imageVector = trailingIcon,
                contentDescription = null,
                tint = Color.DarkGray
            )
        },
        placeholder = {
            Text(text = placeholder, color = colorResource(id = R.color.colorPrimary))
        })
}

@Preview
@Composable
fun IconTextInputPreview() {
    IconTextInput(
        leadingIcon = Icons.Filled.Email,
        trailingIcon = Icons.Rounded.Backspace,
        value = "Please input your email",
        placeholder = "",
        onTrailingIconClick = {},
        onValueChange = {}
    )
}
