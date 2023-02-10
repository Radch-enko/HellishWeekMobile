package com.blesscompany.hellishweek.mobile.android.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.common.utils.isEmailValid
import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthorizationScreenViewModel : ViewModel() {

    data class State(
        val email: String = "",
        val password: String = "",
        val errorMessage: StringResource? = null,
        val isLoading: Boolean = false
    )

    sealed class Event {
        object SignIn : Event()
        class RestorePassword(val email: String) : Event()
        data class TypeEmail(val email: String) : Event()
        data class TypePassword(val password: String) : Event()
    }

    private val mutableStateFlow =
        MutableStateFlow(State(email = "", password = "", errorMessage = null))

    val state = mutableStateFlow.asStateFlow()

    fun sendEvent(event: Event) {
        when (event) {
            Event.SignIn -> {
                viewModelScope.launch {
                    if (isValidForm(state.value.email, state.value.password)) {
                        mutableStateFlow.update { it.copy(isLoading = true) }
                        delay(1500)
                        Timber.d("info = ${state.value}")
                        mutableStateFlow.update { it.copy(isLoading = false) }
                    } else {
                        mutableStateFlow.update {
                            it.copy(
                                errorMessage = Resources.strings.sign_in_form_incorrect
                            )
                        }
                    }
                }
            }
            is Event.RestorePassword -> {
                // TODO("Restore password call request")
            }
            is Event.TypeEmail -> {
                mutableStateFlow.update { it.copy(email = event.email.trim()) }
            }
            is Event.TypePassword -> {
                mutableStateFlow.update { it.copy(password = event.password.trim()) }
            }
        }
    }

    private fun isValidForm(email: String, password: String): Boolean {
        return email.isEmailValid() && password.isNotBlank()
    }
}
