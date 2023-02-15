package com.blesscompany.hellishweek.mobile.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blesscompany.hellishweek.mobile.android.navigation.bottom.BottomBarScreen
import com.blesscompany.hellishweek.mobile.android.screens.main.home.HomeScreenWrapper
import com.blesscompany.hellishweek.mobile.android.screens.main.profile.ProfileScreen
import com.blesscompany.hellishweek.shared.navigation.destination.Graph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainFlowWrapper() {
    val navController = rememberAnimatedNavController()

    Scaffold(modifier = Modifier.navigationBarsPadding(), bottomBar = {
        BottomBar(navController = navController)
    }) { innerValues ->
        Box(modifier = Modifier.padding(innerValues)) {
            AnimatedNavHost(
                navController = navController,
                route = Graph.MAIN,
                startDestination = BottomBarScreen.Home.route
            ) {
                homeGraph(navController)
                composable(route = BottomBarScreen.Profile.route, enterTransition = {
                    fadeIn(
                        animationSpec = tween(100)
                    )
                }, exitTransition = {
                    fadeOut(
                        animationSpec = tween(0)
                    )
                }) {
                    ProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(route = BottomBarScreen.Home.route, enterTransition = {
        fadeIn(
            animationSpec = tween(0)
        )
    }, exitTransition = {
        fadeOut(
            animationSpec = tween(100)
        )
    }) {
        HomeScreenWrapper()
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                tint = if (selected) MaterialTheme.colors.secondary else Color.Gray,
                contentDescription = "Navigation Icon"
            )
        },
        selected = selected,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}