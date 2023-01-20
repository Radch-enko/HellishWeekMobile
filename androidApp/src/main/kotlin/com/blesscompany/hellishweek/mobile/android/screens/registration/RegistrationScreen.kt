package com.blesscompany.hellishweek.mobile.android.screens.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blesscompany.hellishweek.common.utils.Gender
import com.blesscompany.hellishweek.features.registration.PasswordRequirements
import com.blesscompany.hellishweek.mobile.android.ui.Boulder
import com.blesscompany.hellishweek.mobile.android.ui.components.*
import com.blesscompany.hellishweek.resources.Resources
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegistrationScreen(
    selectedCountry: String?,
    onBack: () -> Unit,
    goToAuthorization: () -> Unit,
    viewModel: RegistrationScreenViewModel = koinViewModel(),
    goToCountrySearcher: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.sharedEffect.collectLatest { effect ->
            when (effect) {
                RegistrationScreenViewModel.Effect.CanGoNext -> {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            }
        }
    })

    if (!selectedCountry.isNullOrBlank()) {
        viewModel.sendEvent(RegistrationScreenViewModel.Event.InterCountry(selectedCountry))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BackButton(modifier = Modifier.align(Alignment.CenterStart), onButtonClick = {
                if (pagerState.currentPage == 0) {
                    onBack()
                } else {
                    scope.launch { pagerState.animateScrollToPage(0) }
                }
            })
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = Resources.strings.registration.resourceId),
                style = MaterialTheme.typography.h5,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = stringResource(id = Resources.strings.your_info.resourceId))
        }
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(count = 2, state = pagerState, userScrollEnabled = false) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (currentPage) {
                    0 -> {
                        FirstPartOfRegistrationSlide(
                            state, viewModel::sendEvent, goToCountrySearcher
                        )
                    }
                    1 -> {
                        SecondPartOfRegistrationSlide(state, viewModel::sendEvent)
                    }
                }

                val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                    val str = stringResource(id = Resources.strings.already_have_account.resourceId)
                    val startIndex = str.indexOf("?") + 1
                    val end = str.lastIndex + 1
                    append(str)
                    addStyle(
                        style = SpanStyle(
                            color = Boulder, fontSize = 13.sp, textDecoration = TextDecoration.None
                        ), start = startIndex, end = end
                    )
                    pushStringAnnotation("Action", "SignIn")
                }

                Spacer(modifier = Modifier.height(30.dp))

                ClickableText(text = annotatedLinkString, onClick = {
                    goToAuthorization()
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPagerIndicator(pagerState = pagerState)
        state.error?.let {
            ErrorMessage(value = it.resourceId, style = MaterialTheme.typography.body1)
        }
        Spacer(modifier = Modifier.weight(.5f))
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
            DefaultButton(
                modifier = Modifier
                    .width(140.dp),
                text = if (pagerState.currentPage == 0) stringResource(id = Resources.strings.next.resourceId) else stringResource(
                    id = Resources.strings.registration.resourceId
                ), onClick = {
                    if (pagerState.currentPage == 0) {
                        viewModel.sendEvent(RegistrationScreenViewModel.Event.Next)
                    } else {
                        viewModel.sendEvent(RegistrationScreenViewModel.Event.Registration)
                    }
                },
                isLoading = state.isLoading
            )
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}

@Composable
private fun FirstPartOfRegistrationSlide(
    state: RegistrationScreenViewModel.State,
    sendEvent: (RegistrationScreenViewModel.Event) -> Unit,
    goToCountrySearcher: () -> Unit
) {
    TextFieldDefault(
        value = state.name,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterName(it)) },
        placeholder = stringResource(id = Resources.strings.name_placeholder.resourceId),
        errorMessage = state.nameError?.resourceId?.let { stringResource(id = it) }
    )

    Spacer(modifier = Modifier.height(16.dp))
    DatePickerTextField(
        value = state.date,
        onDateSelected = { date: kotlinx.datetime.LocalDate ->
            sendEvent(
                RegistrationScreenViewModel.Event.InterDate(date)
            )
        },
        placeholder = stringResource(id = Resources.strings.birthday_placeholder.resourceId),
        errorMessage = state.dateError?.resourceId?.let { stringResource(id = it) },
    )

    Spacer(modifier = Modifier.height(16.dp))
    ClickableField(
        value = state.country,
        onClick = goToCountrySearcher,
        placeholder = stringResource(id = Resources.strings.country_placeholder.resourceId),
        errorMessage = state.countryError?.resourceId?.let { stringResource(id = it) }
    )

    Spacer(modifier = Modifier.height(16.dp))

    DropDownTextField(
        text = state.gender?.name.orEmpty(),
        possibleValues = Gender.values().toList(),
        placeholder = stringResource(id = Resources.strings.gender_placeholder.resourceId),
        errorMessage = state.genderError?.resourceId?.let { stringResource(id = it) },
    ) { gender: Gender ->
        sendEvent(
            RegistrationScreenViewModel.Event.InterGender(gender)
        )
    }
}

@Composable
private fun SecondPartOfRegistrationSlide(
    state: RegistrationScreenViewModel.State,
    sendEvent: (RegistrationScreenViewModel.Event) -> Unit
) {
    EmailTextInput(
        value = state.email,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterEmail(it)) },
        placeholder = stringResource(id = Resources.strings.email_placeholder.resourceId),
        errorMessage = state.emailError?.resourceId?.let { stringResource(id = it) }
    )

    Spacer(modifier = Modifier.height(16.dp))
    PasswordTextField(
        value = state.password,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterPassword(it)) },
        placeholder = stringResource(id = Resources.strings.password_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    PasswordTextField(
        value = state.passwordAgain,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterPasswordAgain(it)) },
        placeholder = stringResource(id = Resources.strings.password_again_placeholder.resourceId)
    )
    Spacer(modifier = Modifier.height(16.dp))
    PasswordRequirementsHints(state.passwordRequirements)
}

@Composable
private fun PasswordRequirementsHints(passwordRequirements: Set<PasswordRequirements>) {
    PasswordRequirements.values().forEach { requirement ->
        PasswordReq(
            requirement = requirement, satisfied = passwordRequirements.contains(requirement)
        )
    }
}

@Composable
private fun PasswordReq(
    modifier: Modifier = Modifier, requirement: PasswordRequirements, satisfied: Boolean
) {
    val tint = if (satisfied) {
        MaterialTheme.colors.primary
    } else MaterialTheme.colors.error.copy(alpha = 0.4f)
    Row(
        modifier = modifier.padding(0.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = if (satisfied) Icons.Default.Check else Icons.Default.Close,
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.clearAndSetSemantics { },
            text = requirement.label.toString(context = LocalContext.current),
            fontSize = 12.sp,
            color = tint
        )
    }
}

