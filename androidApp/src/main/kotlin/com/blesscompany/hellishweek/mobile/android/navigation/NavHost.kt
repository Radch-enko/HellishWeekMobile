package com.blesscompany.hellishweek.mobile.android.navigation

import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blesscompany.hellishweek.mobile.android.screens.splash.StartSplashScreen
import com.blesscompany.hellishweek.shared.navigation.destination.Graph
import com.blesscompany.hellishweek.shared.navigation.destination.SplashRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(
    ExperimentalAnimationApi::class
)
@Composable
fun ApplicationRoot(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) Graph.MAIN else SplashRoute.route
    ) {
        composable(SplashRoute.route) {
            StartSplashScreen {
                navController.popBackStack()
                navController.navigate(Graph.MAIN)
            }
        }
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}