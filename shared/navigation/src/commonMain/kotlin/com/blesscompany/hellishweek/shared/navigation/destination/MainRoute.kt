package com.blesscompany.hellishweek.shared.navigation.destination

sealed class MainRoute(val route: String) {

    object Challenges: MainRoute("challenges")

    object Notifications: MainRoute("notifications")
}