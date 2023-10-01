package com.hieuwu.groceriesstore.presentation.utils

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit

@Composable
@ReadOnlyComposable
fun textUnit(@DimenRes id: Int): TextUnit {
    val dimension = dimensionResource(id = id)
    return with(LocalDensity.current) {
        dimension.toSp()
    }
}