package com.hieuwu.gofocus.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface Destination {
    val route: String
    val title: String
}

interface BottomTabDestination : Destination {
    val activeIcon: ImageVector
    val inActiveIcon: ImageVector
}

object TimerDestination : BottomTabDestination {
    // Put information needed to navigate here
    override val route = "timer"
    override val activeIcon = Icons.Filled.Lightbulb
    override val inActiveIcon: ImageVector = Icons.Outlined.Lightbulb
    override val title = "Timer"
}

object TimerReportDestination : BottomTabDestination {
    override val route = "timer_report"
    override val activeIcon = Icons.Filled.Leaderboard
    override val inActiveIcon: ImageVector = Icons.Outlined.Leaderboard
    override val title = "Reports"
}

object TimerSettingsDestination : Destination {
    override val route = "timer_settings"
    override val title = "Settings"
}

object TagsManagementDestination : Destination {
    override val route = "tags_management"
    override val title = "Tags Management"
}

object TimerPeriodDetailsDestination : Destination {
    override val route = "timer_period_details"
    override val title = "Details"
    const val timerPeriodArg = "timer_period_id"
    val arguments = listOf(navArgument(name = timerPeriodArg) {
        type = NavType.StringType
    })
    fun createRouteWithParam(id: String) = "${TimerPeriodDetailsDestination.route}/${id}"

}

val bottomTabRowScreens = listOf(TimerDestination, TimerReportDestination)
