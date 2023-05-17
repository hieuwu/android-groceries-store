package com.hieuwu.groceriesstore.utilities.navigation

import androidx.compose.ui.graphics.vector.ImageVector


interface Destination {
    val route: String
    val title: String
}

interface BottomTabDestination : Destination {
    val activeIcon: ImageVector
    val inActiveIcon: ImageVector
}

object OrderHistoryDestination : Destination {
    override val route = "order_history"
    override val title = "Order History"
}
