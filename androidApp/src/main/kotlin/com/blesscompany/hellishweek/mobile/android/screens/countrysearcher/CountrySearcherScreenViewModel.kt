package com.blesscompany.hellishweek.mobile.android.screens.countrysearcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CountrySearcherScreenViewModel : ViewModel() {

    private val sampleCountryList: List<CountryEntity> = listOf(
        CountryEntity("Russia"),
        CountryEntity("USA"),
        CountryEntity("Britain"),
        CountryEntity("China"),
        CountryEntity("Japan"),
        CountryEntity("Sweden"),
    )


    data class State(
        val countries: List<CountryEntity>,
        val query: String = "",
        val selectedCountry: CountryEntity? = null,
        val isLoading: Boolean = false,
        val errorMessage: StringResource? = null
    )

    private val mutableStateFlow = MutableStateFlow(State(countries = sampleCountryList))
    val state = mutableStateFlow.asStateFlow()

    sealed class Event {
        class OnSelectedCountry(val selectedCountry: CountryEntity) : Event()
        class OnQueryChanged(val newQuery: String) : Event()
    }

    sealed class Effect {
        class OnCountrySelected(val selectedCountry: String) : Effect()
    }

    private val mutableSharedFlow = MutableSharedFlow<Effect>()
    val sharedFlow = mutableSharedFlow.asSharedFlow()

    init {
        collectData()
    }

    private fun collectData() = viewModelScope.launch {
        state.debounce(500).collectLatest { state ->
            if (state.query != "error") {
                val filteredCountries =
                    sampleCountryList.filter { it.name.contains(state.query, ignoreCase = true) }
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        countries = filteredCountries
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        errorMessage = Resources.strings.example_error,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun sendEvent(event: Event) {
        when (event) {
            is Event.OnQueryChanged -> mutableStateFlow.update {
                it.copy(
                    query = event.newQuery,
                    isLoading = true
                )
            }
            is Event.OnSelectedCountry -> viewModelScope.launch {
                mutableStateFlow.update { it.copy(selectedCountry = event.selectedCountry) }
                mutableSharedFlow.emit(
                    Effect.OnCountrySelected(
                        event.selectedCountry.name
                    )
                )
            }
        }
    }
}