package com.hieuwu.groceriesstore.presentation.notificationsettings.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R

@Composable
fun NotificationSettingsItem(@StringRes text: Int, isChecked: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(text))
        Switch(
            checked = isChecked, onCheckedChange = { onClick() }, colors = SwitchDefaults.colors(
                checkedTrackColor = colorResource(id = R.color.colorPrimary)
            )
        )
    }
}

@Preview
@Composable
fun NotificationSettingsItemPreview() {
    NotificationSettingsItem(
        text = R.string.show_when_order_created,
        isChecked = true,
        onClick = { }
    )
}