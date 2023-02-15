package com.blesscompany.hellishweek.mobile.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.blesscompany.hellishweek.shared.navigation.destination.Graph
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable(Graph.MAIN) {
        MainFlowWrapper()
    }
}