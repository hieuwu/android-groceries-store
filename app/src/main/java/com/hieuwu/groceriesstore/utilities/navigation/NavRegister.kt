package com.hieuwu.groceriesstore.utilities.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieuwu.groceriesstore.presentation.orderhistory.OrderHistoryScreen

fun NavGraphBuilder.navRegistration(navController: NavController) {
    composable(OrderHistoryDestination.route) {
        OrderHistoryScreen(
            navController = navController,
        )
    }
}
//    }
//    composable(TimerReportDestination.route) {
//        ReportsScreen(
//            navController = navController
//        )
//    }
//    composable(TimerSettingsDestination.route) {
//        TimerSettingsScreen(
//            navController = navController
//        )
//    }
//
//    composable(TagsManagementDestination.route) {
//        TagsManagementScreen(
//            navController = navController
//        )
//    }
//
//    composable(route = "${TimerPeriodDetailsDestination.route}/{${TimerPeriodDetailsDestination.timerPeriodArg}}",
//        arguments = TimerPeriodDetailsDestination.arguments) { navBackStackEntry ->
//        val timerPeriodId =
//            navBackStackEntry.arguments?.getString(TimerPeriodDetailsDestination.timerPeriodArg)
//
//        TimerPeriodDetailsScreen(timerPeriodId = timerPeriodId ?: "", navController = navController)
//    }

