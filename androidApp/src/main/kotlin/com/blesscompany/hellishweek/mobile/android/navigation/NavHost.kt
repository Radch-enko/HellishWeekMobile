package com.blesscompany.hellishweek.mobile.android.navigation

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blesscompany.hellishweek.mobile.android.navigation.destination.NavRoute
import com.blesscompany.hellishweek.mobile.android.screens.authorization.AuthorizationScreen
import com.blesscompany.hellishweek.mobile.android.screens.preview.PreviewScreen
import com.blesscompany.hellishweek.mobile.android.screens.registration.RegistrationScreen
import com.blesscompany.hellishweek.mobile.android.screens.splash.StartSplashScreen
import com.blesscompany.hellishweek.mobile.android.ui.components.WavesBackground

@Composable
fun ApplicationRoot(navController: NavHostController) {
    Box {
        WavesBackground()
        NavHost(
            navController = navController,
            startDestination = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) NavRoute.Preview.destination else NavRoute.Splash.destination
        ) {
            composable(NavRoute.Splash.destination) {
                StartSplashScreen {
                    navController.popBackStack()
                    navController.navigate(NavRoute.Preview.destination)
                }
            }
            composable("preview") {
                PreviewScreen(
                    goToRegistration = { navController.navigate(NavRoute.Registration.destination) }
                ) { navController.navigate(NavRoute.Authorization.destination) }
            }
            composable(NavRoute.Registration.destination) {
                RegistrationScreen(
                    onBack = { navController.popBackStack() },
                    goToAuthorization = { navController.navigate("authorization") })
            }
            composable(NavRoute.Authorization.destination) {
                AuthorizationScreen(
                    onBack = { navController.popBackStack() },
                    goToRegistration = { navController.navigate("registration") })
            }
        }
    }
}