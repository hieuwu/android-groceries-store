package com.hieuwu.groceriesstore.presentation.core.widgets

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun PrimaryIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = IconButtonDefaults.filledShape,
    content: @Composable () -> Unit
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp),
        enabled = enabled,
        shape = shape,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = colorResource(id = R.color.colorPrimary),
            disabledContainerColor = colorResource(id = R.color.light_gray),
            contentColor = Color.White,
        ),
        content = content
    )
}

@Composable
fun PrimarySquareIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) = PrimaryIconButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = RoundedCornerShape(8.dp),
    content = content
)

@Preview
@Composable
private fun PrimaryIconButtonPreview() {
    PrimaryIconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "")
    }
}

@Preview
@Composable
private fun PrimarySquareIconButtonPreview() {
    PrimarySquareIconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "")
    }
}
