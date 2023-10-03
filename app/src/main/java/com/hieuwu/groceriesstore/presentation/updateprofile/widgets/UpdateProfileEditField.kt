package com.hieuwu.groceriesstore.presentation.updateprofile.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R

@Composable
fun UpdateProfileEditField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = colorResource(id = R.color.colorPrimary),
            surfaceVariant = Color.Transparent,
        ),
    ) {
        TextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            placeholder = placeholder?.let {
                @Composable {
                    Text(
                        text = placeholder,
                        color = colorResource(id = R.color.light_gray)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UpdateProfileEditFieldPreview() {
    var demoText by remember { mutableStateOf("") }
    UpdateProfileEditField(
        modifier = Modifier.fillMaxWidth(),
        value = demoText,
        placeholder = "Demo Text",
        onValueChange = { demoText = it }
    )
}