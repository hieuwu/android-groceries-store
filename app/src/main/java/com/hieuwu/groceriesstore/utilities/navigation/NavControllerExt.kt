package com.hieuwu.groceriesstore.utilities

import androidx.navigation.NavController

fun NavController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}