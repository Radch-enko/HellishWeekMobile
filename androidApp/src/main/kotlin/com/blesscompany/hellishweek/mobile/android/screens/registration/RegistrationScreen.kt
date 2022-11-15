package com.blesscompany.hellishweek.mobile.android.screens.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.blesscompany.hellishweek.mobile.android.ui.Boulder
import com.blesscompany.hellishweek.mobile.android.ui.components.*
import com.blesscompany.hellishweek.resources.Resources
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegistrationScreen(onBack: () -> Unit, goToAuthorization: () -> Unit) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                BackButton(modifier = Modifier.align(Alignment.CenterStart), onButtonClick = onBack)
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
                            FirstPartOfRegistrationSlide()
                        }
                        1 -> {
                            SecondPartOfRegistrationSlide()
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

        DefaultButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 100.dp),
            text = if (pagerState.currentPage == 0) stringResource(id = Resources.strings.next.resourceId) else stringResource(
                id = Resources.strings.registration.resourceId
            ),
            onClick = { scope.launch { pagerState.animateScrollToPage(1) } })
    }
}

@Composable
private fun FirstPartOfRegistrationSlide() {
    var name by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var country by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }

    TextFieldDefault(
        value = name,
        onValueChange = { name = it },
        placeholder = stringResource(id = Resources.strings.name_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldDefault(
        value = date,
        onValueChange = { date = it },
        placeholder = stringResource(id = Resources.strings.birthday_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldDefault(
        value = country,
        onValueChange = { country = it },
        placeholder = stringResource(id = Resources.strings.country_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldDefault(
        value = gender,
        onValueChange = { gender = it },
        placeholder = stringResource(id = Resources.strings.gender_placeholder.resourceId),
    )
}

@Composable
private fun SecondPartOfRegistrationSlide() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordAgain by remember {
        mutableStateOf("")
    }

    EmailTextInput(
        value = email,
        onValueChange = { email = it },
        placeholder = stringResource(id = Resources.strings.email_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    PasswordTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = stringResource(id = Resources.strings.password_placeholder.resourceId),
    )

    Spacer(modifier = Modifier.height(16.dp))
    PasswordTextField(
        value = passwordAgain,
        onValueChange = { passwordAgain = it },
        placeholder = stringResource(id = Resources.strings.password_again_placeholder.resourceId)
    )
}

