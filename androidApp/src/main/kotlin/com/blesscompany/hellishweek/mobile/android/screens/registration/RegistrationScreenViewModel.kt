package com.blesscompany.hellishweek.mobile.android.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blesscompany.hellishweek.common.utils.Gender
import com.blesscompany.hellishweek.common.utils.isEmailValid
import com.blesscompany.hellishweek.features.registration.PasswordRequirements
import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class RegistrationScreenViewModel : ViewModel() {

    data class State(
        val name: String = "",
        val nameError: StringResource? = null,
        val date: LocalDate? = null,
        val dateError: StringResource? = null,
        val country: String = "",
        val countryError: StringResource? = null,
        val gender: Gender? = null,
        val genderError: StringResource? = null,
        val email: String = "",
        val emailError: StringResource? = null,
        val password: String = "",
        val passwordRequirements: Set<PasswordRequirements> = emptySet(),
        val passwordError: StringResource? = null,
        val passwordAgain: String = "",
        val passwordAgainError: StringResource? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) {
        fun isValid(): Boolean {
            return nameError == null && dateError == null && countryError == null && genderError == null && emailError == null && passwordRequirements.containsAll(
                PasswordRequirements.values().toSet()
            ) && passwordAgainError == null
        }
    }

    sealed class Event {
        object Registration : Event()
        class InterName(val name: String) : Event()
        class InterDate(val date: LocalDate?) : Event()
        class InterCountry(val country: String) : Event()
        class InterGender(val gender: Gender) : Event()
        class InterEmail(val email: String) : Event()
        class InterPassword(val password: String) : Event()
        class InterPasswordAgain(val passwordAgain: String) : Event()
    }

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
                mutableStateFlow.update { it.copy(date = event.date) }
            }
            is Event.InterEmail -> {
                mutableStateFlow.update {
                    it.copy(
                        email = event.email,
                        emailError = if (event.email.isEmailValid()
                                .not()
                        ) Resources.strings.email_incorrect else null
                    )
                }
            }
            is Event.InterGender -> {
                mutableStateFlow.update { it.copy(gender = event.gender) }
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
        if (password == state.value.passwordAgain) {
            requirements.add(PasswordRequirements.PASSWORDS_ARE_SAME)
        }

        mutableStateFlow.update {
            it.copy(password = password, passwordRequirements = requirements)
        }
    }

    private fun updatePasswordAgain(passwordAgain: String) {
        val requirements = state.value.passwordRequirements.toMutableSet()
        if (passwordAgain == state.value.password) {
            requirements.add(PasswordRequirements.PASSWORDS_ARE_SAME)
        }

        mutableStateFlow.update {
            it.copy(passwordAgain = passwordAgain, passwordRequirements = requirements)
        }
    }

    private fun doRegistration() = viewModelScope.launch {
        mutableStateFlow.update {
            it.copy(
                dateError = if (it.date == null) Resources.strings.date_is_empty else null,
                genderError = if (it.gender == null) Resources.strings.gender_is_empty else null,
                passwordAgainError = if (it.passwordAgain != it.password) Resources.strings.password_again_incorrect else null
            )
        }

        if (state.value.isValid()) {
            mutableStateFlow.update { it.copy(isLoading = true) }
            delay(1500)
            mutableStateFlow.update { it.copy(isLoading = false) }
        }
    }
}