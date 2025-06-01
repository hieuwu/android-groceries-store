package com.hieuwu.groceriesstore.presentation.account.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.UserModel

@Composable
fun AccountContentView(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
    onSignInClick: () -> Unit,
    onProfileSettingsClick: () -> Unit,
    onNotificationSettingsClick: () -> Unit,
    onOrderHistorySettingsClick: () -> Unit,
    onMealPlanningClick: () -> Unit,
    onSignOutClick: () -> Unit,
) {
    if (user != null) {
        AccountContentSignedInView(
            modifier = modifier,
            onProfileSettingsClick = onProfileSettingsClick,
            onNotificationSettingsClick = onNotificationSettingsClick,
            onOrderHistorySettingsClick = onOrderHistorySettingsClick,
            onSignOutClick = onSignOutClick,
            onMealPlanningClick = onMealPlanningClick
        )
    } else {
        AccountContentSignedOutView(
            modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.margin_medium)),
            onSignInClick = onSignInClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountContentViewSignedInStatePreview() {
    Column {
        AccountContentView(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_medium)),
            user = DemoUser,
            onSignInClick = {},
            onProfileSettingsClick = {},
            onNotificationSettingsClick = {},
            onOrderHistorySettingsClick = {},
            onMealPlanningClick = {}
        ) {}
    }
}