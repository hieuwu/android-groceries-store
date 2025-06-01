package com.hieuwu.groceriesstore.presentation.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hieuwu.groceriesstore.presentation.account.widgets.AccountScreenView
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = koinViewModel(),
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
