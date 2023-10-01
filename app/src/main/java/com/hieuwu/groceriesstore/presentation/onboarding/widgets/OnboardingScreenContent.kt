package com.hieuwu.groceriesstore.presentation.onboarding.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieuwu.groceriesstore.R

@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    navigateToMainInitialScreen: () -> Unit
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_carrot_icon),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.welcome_to_our_store),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = stringResource(R.string.get_your_gorceries_in_as_fast_as_one_hour),
            color = Color.White,
        )
        OnboardingGetStartedButton(
            modifier = Modifier.fillMaxWidth(),
            navigateToMainInitialScreen = navigateToMainInitialScreen
        )
    }
}
