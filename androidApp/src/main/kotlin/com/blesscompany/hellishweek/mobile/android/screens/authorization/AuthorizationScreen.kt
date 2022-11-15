package com.blesscompany.hellishweek.mobile.android.screens.authorization

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

@Composable
fun AuthorizationScreen(onBack: () -> Unit, goToRegistration: () -> Unit) {
    var dialogVisible by remember {
        mutableStateOf(false)
    }

    ForgotPassword(showDialog = dialogVisible, setShowDialog = { dialogVisible = it })
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
                    text = stringResource(id = Resources.strings.sign_in.resourceId),
                    style = MaterialTheme.typography.h5,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var email by remember {
                    mutableStateOf("")
                }
                var password by remember {
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

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(onClick = { dialogVisible = true }) {
                    Text(text = stringResource(id = Resources.strings.remember_my_password.resourceId))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        DefaultButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 100.dp),
            text = stringResource(id = Resources.strings.sign_in.resourceId),
            onClick = { /*TODO*/ })
    }
}

@Composable
private fun ForgotPassword(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
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
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { setShowDialog(false) }) {
                        Icon(Icons.Filled.Close, contentDescription = null)
                    }
                    Text(
                        text = stringResource(id = Resources.strings.restore_my_password.resourceId),
                        style = MaterialTheme.typography.h5
                    )
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
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DefaultButton(
                        text = stringResource(id = Resources.strings.get_reset_code.resourceId),
                        onClick = { /*TODO*/ })
                }
            }
        }
    }
}