package com.blesscompany.hellishweek.mobile.android.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Filled.Home
    )

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Default.Person,
        selectedIcon = Icons.Filled.Person
    )
}