package com.hieuwu.gofocus.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}