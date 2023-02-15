package com.blesscompany.hellishweek.mobile.android.screens.main.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.features.notifications.presentation.NotificationCardUI
import com.blesscompany.hellishweek.mobile.android.data.SampleData
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotificationsScreenViewModel : ViewModel() {

    data class State(
        val isLoading: Boolean = false,
        val error: StringResource? = null,
        val notifications: List<NotificationCardUI> = emptyList()
    )

    sealed class Event {
        class OnQueryChanged(val newQuery: String) : Event()
    }

    private val mutableQueryState = MutableStateFlow("")
    val queryState = mutableQueryState.asStateFlow()

    private val mutableStateFlow = MutableStateFlow(State())
    val state = mutableStateFlow.asStateFlow()

    init {
        listenQueryState()
        loadNotifications()
    }

    fun sendEvent(event: Event) {
        when (event) {
            is Event.OnQueryChanged -> mutableQueryState.value = event.newQuery
        }
    }

    private fun loadNotifications() = viewModelScope.launch {
        mutableStateFlow.update { it.copy(isLoading = true) }
        delay(600)
        mutableStateFlow.update {
            it.copy(
                isLoading = false,
                notifications = SampleData.getNotifications()
            )
        }
    }

    private fun listenQueryState() = viewModelScope.launch {
        mutableQueryState.debounce(300).distinctUntilChanged().collectLatest { query ->
            mutableStateFlow.update { state ->
                state.copy(
                    notifications = filterNotifications(
                        SampleData.getNotifications(),
                        query
                    )
                )
            }
        }
    }

    private fun filterNotifications(
        notifications: List<NotificationCardUI>,
        query: String
    ): List<NotificationCardUI> {
        return notifications.filter { alert ->
            alert.type.contains(query, true) ||
                    alert.author.contains(query, true) ||
                    alert.description.contains(query, true)
        }
    }
}