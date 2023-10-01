package com.hieuwu.groceriesstore.presentation.account.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.presentation.account.DemoUser
import com.hieuwu.groceriesstore.presentation.utils.Converter
import com.hieuwu.groceriesstore.presentation.utils.textUnit

@Composable
fun AccountHeaderView(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.colorPrimary))
            .padding(dimensionResource(id = R.dimen.margin_medium)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        AccountHeaderTitle(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_medium)),
            user = user
        )
        if (user != null) {
            AccountHeaderSignedInView(user = user)
        } else {
            AccountHeaderSignedOutView(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun AccountHeaderTitle(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
) {
    Text(
        modifier = modifier,
        text = Converter.stringToEmpTy(user?.name),
        fontSize = textUnit(id = R.dimen.text_big_bold),
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
}

@Composable
fun AccountHeaderSignedInView(
    modifier: Modifier = Modifier,
    user: UserModel
) {
    val userDetails = listOf(user.address, user.email, user.phone)
    for (userDetail in userDetails) {
        Text(
            modifier = modifier,
            text = userDetail.orEmpty(),
            color = Color.White,
        )
    }
}

@Composable
fun AccountHeaderSignedOutView(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("list_empty.json")
    )
    LottieAnimation(
        modifier = modifier.size(200.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Preview
@Composable
private fun AccountHeaderViewSignedInStatePreview() {
    AccountHeaderView(user = DemoUser)
}

@Preview
@Composable
private fun AccountHeaderViewSignedOutStatePreview() {
    AccountHeaderView()
}