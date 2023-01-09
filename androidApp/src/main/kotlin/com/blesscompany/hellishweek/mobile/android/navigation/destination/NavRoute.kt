package com.blesscompany.hellishweek.mobile.android.navigation.destination

sealed class NavRoute(val destination: String) {
    object Splash: NavRoute("splash")
    object Preview: NavRoute("preview")
    object Registration: NavRoute("registration")
    object Authorization: NavRoute("authorization")
}
