package com.hieuwu.groceriesstore.presentation.account.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.core.widgets.SecondaryButton

@Composable
fun AccountContentSignedInView(
    modifier: Modifier = Modifier,
    onProfileSettingsClick: (() -> Unit),
    onNotificationSettingsClick: (() -> Unit),
    onOrderHistorySettingsClick: (() -> Unit),
    onMealPlanningClick: (() -> Unit),
    onSignOutClick: (() -> Unit),
) {
    val actions = listOf(
        R.string.profile_information to onProfileSettingsClick,
        R.string.notification_settings to onNotificationSettingsClick,
        R.string.order_history_settings to onOrderHistorySettingsClick,
        R.string.meal_planning_settings to onMealPlanningClick,
    )
    for (action in actions) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.margin_small))
                .clickable(onClick = action.second),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = action.first),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF808080),
                maxLines = 1
            )
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small)),
                painter = painterResource(id = R.drawable.ic_baseline_chevron_right_24),
                contentDescription = null,
            )
        }
    }

    SecondaryButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.margin_medium)),
        onClick = onSignOutClick
    ) {
        Text(text = stringResource(id = R.string.sign_out))
    }
}
