package com.blesscompany.hellishweek.mobile.android.screens.main.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.blesscompany.hellishweek.mobile.android.navigation.bottom.BottomBarScreen
import com.blesscompany.hellishweek.mobile.android.screens.main.notifications.NotificationsScreen
import com.blesscompany.hellishweek.shared.navigation.destination.MainRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenWrapper() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        route = BottomBarScreen.Home.route,
        startDestination = MainRoute.Challenges.route
    ) {
        composable(MainRoute.Challenges.route) {
            HomeScreen { navController.navigate(MainRoute.Notifications.route) }
        }
        composable(MainRoute.Notifications.route) {
            NotificationsScreen { navController.popBackStack() }
        }
    }
}