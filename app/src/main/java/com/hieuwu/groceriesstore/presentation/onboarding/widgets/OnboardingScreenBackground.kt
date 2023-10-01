package com.hieuwu.groceriesstore.presentation.onboarding.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R

@Composable
fun OnboardingScreenBackground(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.onboarding_background),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenBackgroundPreview() {
    OnboardingScreenBackground(
        modifier = Modifier.fillMaxSize()
    )
}
