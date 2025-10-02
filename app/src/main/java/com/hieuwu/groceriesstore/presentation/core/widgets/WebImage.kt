package com.hieuwu.groceriesstore.presentation.core.widgets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.SubcomposeAsyncImage
import com.hieuwu.groceriesstore.R

@Composable
fun WebImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
) = SubcomposeAsyncImage(
    model = model,
    contentDescription = contentDescription,
    modifier = modifier,
    alignment = alignment,
    contentScale = contentScale,
    loading = {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.loading_animation),
            contentDescription = null,
        )
    },
    error = {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = null,
        )
    }
)
