package com.hieuwu.groceriesstore.presentation.account.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimaryButton


@Composable
fun AccountContentSignedOutView(
    modifier: Modifier = Modifier,
    onSignInClick: (() -> Unit),
) {
    PrimaryButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onSignInClick
    ) {
        Text(text = stringResource(id = R.string.sign_in))
    }
}

@Preview
@Composable
private fun AccountContentViewSignedOutStatePreview() {
    Column {
        AccountContentView(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_medium)),
            onSignInClick = {},
            onProfileSettingsClick = {},
            onNotificationSettingsClick = {},
            onOrderHistorySettingsClick = {},
            onMealPlanningClick = {}
        ) {}
    }
}