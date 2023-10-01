package com.hieuwu.groceriesstore.presentation.core.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {

    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(id = R.color.colorPrimary),
            disabledContentColor = colorResource(id = R.color.colorPrimary).copy(
                alpha = SecondaryButtonTokens.DisabledLabelTextOpacity
            ),
        ),
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.colorPrimary).copy(
                alpha = if (!enabled) SecondaryButtonTokens.DisabledContainerOpacity else 1f
            )
        ),
        content = content
    )
}

internal object SecondaryButtonTokens {
    const val DisabledContainerOpacity = 0.12f
    const val DisabledLabelTextOpacity = 0.38f
}

@Preview
@Composable
private fun EnabledSecondaryButtonPreview() {
    SecondaryButton(onClick = {}) {
        Text(text = "Enabled")
    }
}

@Preview
@Composable
private fun DisabledSecondaryButtonPreview() {
    SecondaryButton(onClick = {}, enabled = false) {
        Text(text = "Disabled")
    }
}