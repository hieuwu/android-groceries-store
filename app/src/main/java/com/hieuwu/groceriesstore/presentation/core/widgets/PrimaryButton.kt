package com.hieuwu.groceriesstore.presentation.core.widgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {

    Button(
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.colorPrimary),
            disabledContainerColor = colorResource(id = R.color.light_gray),
            contentColor = Color.White,
        ),
        content = content
    )
}
@Preview
@Composable
private fun EnabledPrimaryButtonPreview() {
    PrimaryButton(onClick = {}) {
        Text(text = "Enabled")
    }
}

@Preview
@Composable
private fun DisabledPrimaryButtonPreview() {
    PrimaryButton(onClick = {}, enabled = false) {
        Text(text = "Disabled")
    }
}