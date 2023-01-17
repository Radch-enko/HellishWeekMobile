package com.blesscompany.hellishweek.mobile.android.navigation

import android.os.Build
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blesscompany.hellishweek.mobile.android.screens.authorization.AuthorizationScreen
import com.blesscompany.hellishweek.mobile.android.screens.preview.PreviewScreen
import com.blesscompany.hellishweek.mobile.android.screens.registration.RegistrationScreen
import com.blesscompany.hellishweek.mobile.android.screens.splash.StartSplashScreen
import com.blesscompany.hellishweek.mobile.android.ui.components.WavesBackground
import com.blesscompany.hellishweek.shared.navigation.destination.NavRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ApplicationRoot(navController: NavHostController) {
    Box {
        WavesBackground()
        AnimatedNavHost(
            navController = navController,
            startDestination = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) NavRoute.Preview.destination else NavRoute.Splash.destination
        ) {
            composable(NavRoute.Splash.destination) {
                StartSplashScreen {
                    navController.popBackStack()
                    navController.navigate(NavRoute.Preview.destination)
                }
            }
            composable(NavRoute.Preview.destination) {
                PreviewScreen(
                    goToRegistration = { navController.navigate(NavRoute.Registration.destination) }
                ) { navController.navigate(NavRoute.Authorization.destination) }
            }
            composable(NavRoute.Registration.destination, enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )

                }) {
                RegistrationScreen(
                    onBack = { navController.popBackStack() },
                    goToAuthorization = { navController.navigate(NavRoute.Authorization.destination) })
            }
            composable(NavRoute.Authorization.destination, enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )

                }) {
                AuthorizationScreen(
                    onBack = { navController.popBackStack() },
                    goToRegistration = { navController.navigate(NavRoute.Registration.destination) })
            }
        }
    }
}