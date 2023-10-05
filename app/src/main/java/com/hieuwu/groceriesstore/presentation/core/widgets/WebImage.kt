package com.hieuwu.groceriesstore.presentation.core.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.hieuwu.groceriesstore.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WebImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
) = GlideImage(
    model = model,
    contentDescription = contentDescription,
    modifier = modifier,
    alignment = alignment,
    contentScale = contentScale,
    loading = placeholder(R.drawable.loading_animation),
    failure = placeholder(R.drawable.ic_broken_image)
)
