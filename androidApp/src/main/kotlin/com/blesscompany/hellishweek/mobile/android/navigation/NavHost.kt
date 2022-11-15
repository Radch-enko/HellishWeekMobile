package com.blesscompany.hellishweek.mobile.android.navigation

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            startDestination = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) "preview" else "splash"
        ) {
            composable("splash") {
                StartSplashScreen {
                    navController.popBackStack()
                    navController.navigate("preview")
                }
            }
            composable("preview") {
                PreviewScreen(
                    goToRegistration = { navController.navigate("registration") }
                ) { navController.navigate("authorization") }
            }
            composable("registration") {
                RegistrationScreen(
                    onBack = { navController.popBackStack() },
                    goToAuthorization = { navController.navigate("authorization") })
            }
            composable("authorization") {
                AuthorizationScreen(
                    onBack = { navController.popBackStack() },
                    goToRegistration = { navController.navigate("registration") })
            }
        }
    }
}