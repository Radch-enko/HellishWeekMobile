package com.blesscompany.hellishweek.mobile.android.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.features.home.ChallengeCardUI
import com.blesscompany.hellishweek.mobile.android.data.SampleData
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    data class State(
        val isLoading: Boolean = false,
        val error: StringResource? = null,
        val challenges: List<ChallengeCardUI> = emptyList()
    )

    sealed class Event {
        class OnQueryChanged(val newQuery: String) : Event()
    }

    private val mutableStateFlow = MutableStateFlow(State())
    val state = mutableStateFlow.asStateFlow()

    private val mutableQueryState = MutableStateFlow("")
    val queryState = mutableQueryState.asStateFlow()

    init {
        listenQueryState()
        loadChallenges()
    }

    private fun loadChallenges() = viewModelScope.launch {
        mutableStateFlow.update { it.copy(isLoading = true) }
        delay(600)
        val sampleChallenges = SampleData.getChallenges()
        mutableStateFlow.update { it.copy(challenges = sampleChallenges, isLoading = false) }
    }

    private fun listenQueryState() = viewModelScope.launch {
        mutableQueryState.debounce(300).distinctUntilChanged().collectLatest { newQuery ->
            mutableStateFlow.update { state ->
                state.copy(
                    challenges = filterChallengesByQuery(
                        SampleData.getChallenges(),
                        newQuery
                    )
                )
            }
        }
    }

    private fun filterChallengesByQuery(
        challenges: List<ChallengeCardUI>,
        newQuery: String
    ): List<ChallengeCardUI> {
        return challenges.filter { challengeModel ->
            challengeModel.title.contains(
                newQuery, true
            ) || challengeModel.description.contains(newQuery, true)
        }
    }

    fun sendEvent(event: Event) {
        when (event) {
            is Event.OnQueryChanged -> mutableQueryState.value = event.newQuery
        }
    }
}