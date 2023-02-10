package com.blesscompany.hellishweek.mobile.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.blesscompany.hellishweek.shared.navigation.destination.Graph
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    composable(Graph.AUTHENTICATION) {
        AuthorizationFlowWrapper { navController.navigate("home") }
    }
}