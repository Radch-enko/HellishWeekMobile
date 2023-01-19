package com.blesscompany.hellishweek.shared.navigation.destination

sealed class AuthRoute(val route: String){
    object Preview: AuthRoute("preview")
    object Registration: AuthRoute("registration")
    object Authorization: AuthRoute("authorization")
    object CountrySearcher: AuthRoute("country_searcher")
}