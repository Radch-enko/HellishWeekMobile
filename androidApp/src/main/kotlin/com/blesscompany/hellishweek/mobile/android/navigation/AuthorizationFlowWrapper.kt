package com.blesscompany.hellishweek.mobile.android.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.blesscompany.hellishweek.mobile.android.screens.authorization.AuthorizationScreen
import com.blesscompany.hellishweek.mobile.android.screens.countrysearcher.CountrySearcherScreen
import com.blesscompany.hellishweek.mobile.android.screens.preview.PreviewScreen
import com.blesscompany.hellishweek.mobile.android.screens.registration.RegistrationScreen
import com.blesscompany.hellishweek.mobile.android.ui.components.WavesBackground
import com.blesscompany.hellishweek.shared.navigation.destination.AuthRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthorizationFlowWrapper(
    onAuthorization: () -> Unit
) {
    val navController = rememberAnimatedNavController()

    WavesBackground()
    AnimatedNavHost(navController = navController, startDestination = AuthRoute.Preview.route) {
        composable(AuthRoute.Preview.route) {
            PreviewScreen(
                goToRegistration = { navController.navigate(AuthRoute.Registration.route) },
                openMainGraph = { onAuthorization() }
            ) { navController.navigate(AuthRoute.Authorization.route) }
        }
        composable(
            AuthRoute.Registration.route, enterTransition = {
                when (initialState.destination.route) {
                    AuthRoute.CountrySearcher.route -> null
                    else -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    AuthRoute.CountrySearcher.route -> null
                    else -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            }) { backStackEntry ->
            val data = backStackEntry
                .savedStateHandle.get<String>("country")

            RegistrationScreen(
                data,
                onBack = { navController.popBackStack() },
                goToAuthorization = { navController.navigate(AuthRoute.Authorization.route) },
                goToCountrySearcher = { navController.navigate(AuthRoute.CountrySearcher.route) })
        }
        composable(AuthRoute.CountrySearcher.route, enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(400)
            )
        }, exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(400)
            )
        }) {
            CountrySearcherScreen { selectedCountry ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "country",
                    selectedCountry
                )
                navController.popBackStack()
            }
        }
        composable(
            AuthRoute.Authorization.route, enterTransition = {
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
                goToRegistration = { navController.navigate(AuthRoute.Registration.route) })
        }
    }
}