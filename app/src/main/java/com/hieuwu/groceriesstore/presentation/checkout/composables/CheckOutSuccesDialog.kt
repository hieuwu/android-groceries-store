package com.hieuwu.groceriesstore.presentation.checkout.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimaryButton

@Composable
fun CheckoutSuccessDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxWidth()
        ) {
            // Lottie Animation
            val lottieComposition by rememberLottieComposition(
                spec = LottieCompositionSpec.Asset("firework.json")
            )
            LottieAnimation(
                modifier = modifier
                    .size(280.dp)
                    .align(Alignment.Center),
                composition = lottieComposition,
                iterations = LottieConstants.IterateForever,
            )

            // Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )

                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(id = R.string.order_is_created_successfully),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                PrimaryButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {
                    Text(text = stringResource(id = R.string.continue_shopping))
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true, showBackground = false
)
@Composable
fun CheckoutSuccessPreview() {
    CheckoutSuccessDialog(onDismiss = {})
}
