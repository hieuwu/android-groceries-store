package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.notificationsettings.composables.NotificationSettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(
    onNavigateUp: () -> Unit,
    viewModel: NotificationSettingsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Notifications", color = Color.White, fontWeight = FontWeight.Medium) },
                actions = {
                    ClickableText(
                        text = AnnotatedString("SAVE"),
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Medium),
                        onClick = {
                            viewModel.updateNotificationSettings()
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.colorPrimary)
                )
            )
        }
    ) {
        val user = viewModel.user.collectAsState().value

        if (user != null)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                var isOrderCreatedNotiEnabled by remember { mutableStateOf(user.isOrderCreatedNotiEnabled) }
                var isDataRefreshedNotiEnabled by remember { mutableStateOf(user.isDataRefreshedNotiEnabled) }
                var isPromotionNotiEnabled by remember { mutableStateOf(user.isPromotionNotiEnabled) }
                viewModel.initializeSwitchValue(
                    user.copy(
                        isOrderCreatedNotiEnabled = isOrderCreatedNotiEnabled,
                        isDataRefreshedNotiEnabled = isDataRefreshedNotiEnabled,
                        isPromotionNotiEnabled = isPromotionNotiEnabled
                    )
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item {
                        NotificationSettingsItem(
                            text = R.string.show_when_order_created,
                            isChecked = isOrderCreatedNotiEnabled,
                            onClick = {
                                isOrderCreatedNotiEnabled = !isOrderCreatedNotiEnabled
                            }
                        )
                    }
                    item {
                        NotificationSettingsItem(
                            text = R.string.show_when_promotion_sent,
                            isChecked = isPromotionNotiEnabled,
                            onClick = {
                                isPromotionNotiEnabled = !isPromotionNotiEnabled
                            }
                        )
                    }
                    item {
                        NotificationSettingsItem(
                            text = R.string.show_when_app_data_is_refreshed,
                            isChecked = isDataRefreshedNotiEnabled,
                            onClick = {
                                isDataRefreshedNotiEnabled = !isDataRefreshedNotiEnabled
                            }
                        )
                    }
                }

            }
    }
}

@Preview
@Composable
fun NotificationSettingsScreenPreview() {
    NotificationSettingsScreen(onNavigateUp = { })
}