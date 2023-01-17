package com.blesscompany.hellishweek.mobile.android.screens.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blesscompany.hellishweek.common.utils.Gender
import com.blesscompany.hellishweek.mobile.android.ui.Boulder
import com.blesscompany.hellishweek.mobile.android.ui.components.*
import com.blesscompany.hellishweek.resources.Resources
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegistrationScreen(
    onBack: () -> Unit,
    goToAuthorization: () -> Unit,
    viewModel: RegistrationScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                BackButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onButtonClick = {
                        if (pagerState.currentPage == 0) {
                            onBack()
                        } else {
                            scope.launch { pagerState.animateScrollToPage(0) }
                        }
                    }
                )
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
                            FirstPartOfRegistrationSlide(state, viewModel::sendEvent)
                        }
                        1 -> {
                            SecondPartOfRegistrationSlide(state, viewModel::sendEvent)
                        }
                    }

                    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                        val str =
                            stringResource(id = Resources.strings.already_have_account.resourceId)
                        val startIndex = str.indexOf("?") + 1
                        val end = str.lastIndex + 1
                        append(str)
                        addStyle(
                            style = SpanStyle(
                                color = Boulder,
                                fontSize = 13.sp,
                                textDecoration = TextDecoration.None
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
        }

        DefaultButton(
            text = if (pagerState.currentPage == 0) stringResource(id = Resources.strings.next.resourceId) else stringResource(
                id = Resources.strings.registration.resourceId
            ),
            onClick = { scope.launch { pagerState.animateScrollToPage(1) } },
            modifier = Modifier
                .align(Alignment.BottomEnd),
            isLoading = state.isLoading
        )
    }
}

@Composable
private fun FirstPartOfRegistrationSlide(
    state: RegistrationScreenViewModel.State,
    sendEvent: (RegistrationScreenViewModel.Event) -> Unit
) {
    TextFieldDefault(
        value = state.name,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterName(it)) },
        placeholder = stringResource(id = Resources.strings.name_placeholder.resourceId),
        errorMessage = state.nameError?.resourceId?.let { stringResource(id = it) }
    )

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldDefault(
        value = state.date.toString(),
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterDate(null)) },
        placeholder = stringResource(id = Resources.strings.birthday_placeholder.resourceId),
        errorMessage = state.dateError?.resourceId?.let { stringResource(id = it) }
    )

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldDefault(
        value = state.country,
        onValueChange = { sendEvent(RegistrationScreenViewModel.Event.InterCountry(it)) },
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
}

