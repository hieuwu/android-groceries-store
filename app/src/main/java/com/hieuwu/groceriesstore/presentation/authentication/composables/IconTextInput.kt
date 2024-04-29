package com.hieuwu.groceriesstore.presentation.authentication.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconTextInput(
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    type: KeyboardType = KeyboardType.Email,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit
) {
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = type),
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
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = colorResource(id = R.color.primary_button)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
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
