package com.blesscompany.hellishweek.mobile.android.screens.authorization

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.blesscompany.hellishweek.mobile.android.ui.Boulder
import com.blesscompany.hellishweek.mobile.android.ui.components.*
import com.blesscompany.hellishweek.resources.Resources
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    onBack: () -> Unit,
    goToRegistration: () -> Unit,
    viewModel: AuthorizationScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var dialogVisible by remember {
        mutableStateOf(false)
    }
    ForgotPasswordDialog(
        showDialog = dialogVisible,
        setShowDialog = { dialogVisible = it },
        onResetCodeButtonClick = { email ->
            viewModel.sendEvent(
                AuthorizationScreenViewModel.Event.RestorePassword(email)
            )
        })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthorizationForm(
                onBack = onBack,
                state = state,
                sendEvent = viewModel::sendEvent
            )

            val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                val str =
                    stringResource(id = Resources.strings.not_registered_yet.resourceId)
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
            }

            Spacer(modifier = Modifier.height(30.dp))

            ClickableText(text = annotatedLinkString, onClick = {
                goToRegistration()
            })

            TextButton(onClick = { dialogVisible = true }) {
                Text(text = stringResource(id = Resources.strings.remember_my_password.resourceId))
            }
        }

        DefaultButton(
            modifier = Modifier
                .width(120.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 100.dp),
            text = stringResource(id = Resources.strings.sign_in.resourceId),
            onClick = { viewModel.sendEvent(AuthorizationScreenViewModel.Event.SignIn) },
            isLoading = state.isLoading
        )
    }
}

@Composable
fun AuthorizationForm(
    state: AuthorizationScreenViewModel.State,
    onBack: () -> Unit,
    sendEvent: (AuthorizationScreenViewModel.Event) -> Unit
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BackButton(modifier = Modifier.align(Alignment.CenterStart), onButtonClick = onBack)
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = Resources.strings.sign_in.resourceId),
                style = MaterialTheme.typography.h5,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = stringResource(id = Resources.strings.your_info.resourceId))
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmailTextInput(
                value = state.email,
                onValueChange = { sendEvent(AuthorizationScreenViewModel.Event.TypeEmail(it)) },
                placeholder = stringResource(id = Resources.strings.email_placeholder.resourceId),
                errorMessage = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = state.password,
                onValueChange = { sendEvent(AuthorizationScreenViewModel.Event.TypePassword(it)) },
                placeholder = stringResource(id = Resources.strings.password_placeholder.resourceId)
            )

            AnimatedVisibility(visible = state.errorMessage != null) {
                state.errorMessage?.let { value ->
                    ErrorMessage(modifier = Modifier.padding(vertical = 8.dp), value.resourceId)
                }
            }
        }
    }
}

@Composable
private fun ForgotPasswordDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    onResetCodeButtonClick: (email: String) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    if (showDialog) {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(10)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DialogTitle(
                        title = stringResource(id = Resources.strings.restore_my_password.resourceId),
                        onBack = { setShowDialog(false) })
                    Spacer(modifier = Modifier.height(16.dp))
                    MediumAlphaText(
                        text = stringResource(id = Resources.strings.restore_my_password_desc.resourceId),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    EmailTextInput(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = stringResource(
                            id = Resources.strings.email_placeholder.resourceId
                        ),
                        errorMessage = null
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DefaultButton(
                        text = stringResource(id = Resources.strings.get_reset_code.resourceId),
                        onClick = { onResetCodeButtonClick(email) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogTitle(title: String, onBack: () -> Unit) {
    val appBarHorizontalPadding = 4.dp
    val titleIconModifier = Modifier
        .fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)

    TopAppBar(
        backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.height(32.dp)) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = onBack,
                    enabled = true,
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Back",
                    )
                }
            }
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = title
                        )
                    }
                }
            }
        }
    }
}
