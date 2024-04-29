package com.hieuwu.groceriesstore.presentation.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.presentation.account.widgets.AccountContentView
import com.hieuwu.groceriesstore.presentation.account.widgets.AccountHeaderView

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    onSignInClick: (() -> Unit),
    onProfileSettingsClick: (() -> Unit),
    onNotificationSettingsClick: (() -> Unit),
    onOrderHistorySettingsClick: (() -> Unit),
    onMealPlanningClick: (() -> Unit),

    ) {
    val user = viewModel.user.collectAsState()

    AccountScreenView(
        modifier = modifier,
        user = user.value,
        onSignInClick = onSignInClick,
        onProfileSettingsClick = onProfileSettingsClick,
        onNotificationSettingsClick = onNotificationSettingsClick,
        onOrderHistorySettingsClick = onOrderHistorySettingsClick,
        onSignOutClick = { viewModel.signOut() },
        onMealPlanningClick = onMealPlanningClick
    )
}

@Composable
private fun AccountScreenView(
    modifier: Modifier = Modifier,
    user: UserModel? = null,
    onSignInClick: () -> Unit,
    onProfileSettingsClick: () -> Unit,
    onNotificationSettingsClick: () -> Unit,
    onOrderHistorySettingsClick: () -> Unit,
    onMealPlanningClick: () -> Unit,
    onSignOutClick: () -> Unit,
) {
    Scaffold { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding),
            verticalArrangement = Arrangement.Top
        ) {

            AccountHeaderView(user = user)
            AccountContentView(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_medium)),
                user = user,
                onSignInClick = onSignInClick,
                onProfileSettingsClick = onProfileSettingsClick,
                onNotificationSettingsClick = onNotificationSettingsClick,
                onOrderHistorySettingsClick = onOrderHistorySettingsClick,
                onSignOutClick = onSignOutClick,
                onMealPlanningClick = onMealPlanningClick,
            )
        }
    }
}

internal val DemoUser = UserModel(
    "0", "J.K. Rowling", "j.k@rowling.com",
    phone = "+1234567890",
    address = "Earth",
    isOrderCreatedNotiEnabled = false,
    isPromotionNotiEnabled = false,
    isDataRefreshedNotiEnabled = false
)

@Preview(showSystemUi = true)
@Composable
private fun SignedInAccountScreen() {
    AccountScreenView(
        user = DemoUser,
        onSignInClick = { /*TODO*/ },
        onProfileSettingsClick = { /*TODO*/ },
        onNotificationSettingsClick = { /*TODO*/ },
        onOrderHistorySettingsClick = { /*TODO*/ },
        onSignOutClick = { /*TODO*/ },
        onMealPlanningClick = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SignedOutAccountScreen() {
    AccountScreenView(
        onSignInClick = { /*TODO*/ },
        onProfileSettingsClick = { /*TODO*/ },
        onNotificationSettingsClick = { /*TODO*/ },
        onOrderHistorySettingsClick = { /*TODO*/ },
        onSignOutClick = { /*TODO*/ },
        onMealPlanningClick = {}
    )
}