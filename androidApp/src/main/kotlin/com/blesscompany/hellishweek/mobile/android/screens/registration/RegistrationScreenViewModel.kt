package com.blesscompany.hellishweek.mobile.android.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.common.utils.Gender
import com.blesscompany.hellishweek.common.utils.isEmailValid
import com.blesscompany.hellishweek.features.registration.PasswordRequirements
import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class RegistrationScreenViewModel : ViewModel() {

    data class State(
        val name: String = "",
        val nameError: StringResource? = null,
        val date: String? = null,
        val dateError: StringResource? = null,
        val country: String = "",
        val countryError: StringResource? = null,
        val gender: Gender? = null,
        val genderError: StringResource? = null,
        val email: String = "",
        val emailError: StringResource? = null,
        val password: String = "",
        val passwordRequirements: Set<PasswordRequirements> = emptySet(),
        val passwordAgain: String = "",
        val isLoading: Boolean = false,
        val error: StringResource? = null
    ) {
        fun isValid(): Boolean {
            return isFirstPartValid() && isLastPartValid()
        }

        fun isFirstPartValid(): Boolean =
            nameError == null && dateError == null && countryError == null && genderError == null

        fun isLastPartValid(): Boolean = emailError == null && passwordRequirements.containsAll(
            PasswordRequirements.values().toSet()
        )
    }

    sealed class Event {
        object Registration : Event()
        object Next : Event()

        class InterName(val name: String) : Event()
        class InterDate(val date: String?) : Event()
        class InterCountry(val country: String) : Event()
        class InterGender(val gender: Gender) : Event()
        class InterEmail(val email: String) : Event()
        class InterPassword(val password: String) : Event()
        class InterPasswordAgain(val passwordAgain: String) : Event()
    }

    sealed class Effect {
        object CanGoNext : Effect()
    }

    private val mutableEffect = MutableSharedFlow<Effect>()
    val sharedEffect = mutableEffect.asSharedFlow()

    private val mutableStateFlow = MutableStateFlow(State())
    val state = mutableStateFlow.asStateFlow()

    fun sendEvent(event: Event) {
        when (event) {
            is Event.InterCountry -> {
                mutableStateFlow.update {
                    it.copy(
                        country = event.country,
                        countryError = if (event.country.isBlank()) Resources.strings.country_is_empty else null
                    )
                }
            }
            is Event.InterDate -> {
                mutableStateFlow.update {
                    it.copy(
                        date = event.date,
                        dateError = if (event.date == null) Resources.strings.date_is_empty else null
                    )
                }
            }
            is Event.InterEmail -> {
                mutableStateFlow.update {
                    it.copy(
                        email = event.email.trim()
                    )
                }
            }
            is Event.InterGender -> {
                mutableStateFlow.update {
                    it.copy(
                        gender = event.gender,
                        genderError = null
                    )
                }
            }
            is Event.InterName -> {
                mutableStateFlow.update {
                    it.copy(
                        name = event.name,
                        nameError = if (event.name.isBlank()) Resources.strings.name_is_empty else null
                    )
                }
            }
            is Event.InterPassword -> updatePassword(event.password)
            is Event.InterPasswordAgain -> updatePasswordAgain(event.passwordAgain)
            Event.Registration -> doRegistration()
            Event.Next -> doNext()
        }
    }

    private fun updatePassword(password: String) {
        val requirements = mutableSetOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        if (password.trim() == state.value.passwordAgain.trim() && password.trim().isNotEmpty()) {
            requirements.add(PasswordRequirements.PASSWORDS_ARE_SAME)
        }

        mutableStateFlow.update {
            it.copy(password = password.trim(), passwordRequirements = requirements)
        }
    }

    private fun updatePasswordAgain(passwordAgain: String) {
        mutableStateFlow.update {
            it.copy(passwordAgain = passwordAgain.trim())
        }
        updatePassword(state.value.password)
    }

    private fun validateFieldsOfFirstPart() {
        mutableStateFlow.update { it.copy(countryError = if (it.country.isBlank()) Resources.strings.country_is_empty else null) }
        mutableStateFlow.update { it.copy(dateError = if (it.date == null) Resources.strings.date_is_empty else null) }
        mutableStateFlow.update {
            it.copy(
                name = it.name.trim(),
                nameError = if (it.name.isBlank()) Resources.strings.name_is_empty else null
            )
        }
        mutableStateFlow.update { it.copy(genderError = if (it.gender == null) Resources.strings.gender_is_empty else null) }
    }

    private fun validateFieldsOfSecondPart() {
        mutableStateFlow.update {
            it.copy(
                emailError = if (state.value.email.isEmailValid().not()
                ) Resources.strings.email_incorrect else null
            )
        }
        updatePassword(state.value.password)
        updatePasswordAgain(state.value.passwordAgain)
    }

    private fun doNext() = viewModelScope.launch {
        validateFieldsOfFirstPart()
        if (state.value.isFirstPartValid()) {
            mutableEffect.emit(Effect.CanGoNext)
        }
    }

    private fun doRegistration() = viewModelScope.launch {
        validateFieldsOfSecondPart()

        if (state.value.isValid()) {
            mutableStateFlow.update { it.copy(isLoading = true) }
            delay(1500)
            mutableStateFlow.update { it.copy(isLoading = false) }
            exampleNetworkRequest(state.value)
        }
    }

    private fun exampleNetworkRequest(value: State) {
        Timber.d("User info: $value")
    }
}