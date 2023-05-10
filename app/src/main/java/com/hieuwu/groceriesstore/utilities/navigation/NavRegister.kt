package com.hieuwu.gofocus.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieuwu.gofocus.presentation.feature.reports.ReportsScreen
import com.hieuwu.gofocus.presentation.feature.tagsmanagement.TagsManagementScreen
import com.hieuwu.gofocus.presentation.feature.timer.TimerScreen
import com.hieuwu.gofocus.presentation.feature.timerperioddetails.TimerPeriodDetailsScreen
import com.hieuwu.gofocus.presentation.feature.timersettings.TimerSettingsScreen

fun NavGraphBuilder.navRegistration(navController: NavController) {
    composable(TimerDestination.route) {
        TimerScreen(
            navController = navController,
        )
    }
    composable(TimerReportDestination.route) {
        ReportsScreen(
            navController = navController
        )
    }
    composable(TimerSettingsDestination.route) {
        TimerSettingsScreen(
            navController = navController
        )
    }

    composable(TagsManagementDestination.route) {
        TagsManagementScreen(
            navController = navController
        )
    }

    composable(route = "${TimerPeriodDetailsDestination.route}/{${TimerPeriodDetailsDestination.timerPeriodArg}}",
        arguments = TimerPeriodDetailsDestination.arguments) { navBackStackEntry ->
        val timerPeriodId =
            navBackStackEntry.arguments?.getString(TimerPeriodDetailsDestination.timerPeriodArg)

        TimerPeriodDetailsScreen(timerPeriodId = timerPeriodId ?: "", navController = navController)
    }

}