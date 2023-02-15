package com.blesscompany.hellishweek.mobile.android.di

import com.blesscompany.hellishweek.mobile.android.screens.authorization.AuthorizationScreenViewModel
import com.blesscompany.hellishweek.mobile.android.screens.countrysearcher.CountrySearcherScreenViewModel
import com.blesscompany.hellishweek.mobile.android.screens.main.home.HomeScreenViewModel
import com.blesscompany.hellishweek.mobile.android.screens.registration.RegistrationScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::AuthorizationScreenViewModel)
    viewModelOf(::RegistrationScreenViewModel)
    viewModelOf(::CountrySearcherScreenViewModel)
    viewModelOf(::HomeScreenViewModel)
}