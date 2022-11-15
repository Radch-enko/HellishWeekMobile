package com.blesscompany.hellishweek.mobile.android.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blesscompany.hellishweek.resources.Resources
import kotlinx.coroutines.delay

@Composable
fun StartSplashScreen(onComplete: () -> Unit) {

    var visible by remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = Color.Transparent,
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            delayMillis = 2000,
                            easing = LinearEasing
                        )
                    )
                ) {
                    Column(
                        verticalArrangement =
                        Arrangement.Center,
                        horizontalAlignment =
                        Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(
                                0.dp,
                                0.dp,
                                0.dp,
                                0.dp
                            )
                            .fillMaxSize()
                    ) {
                        Surface(
                            shape = RectangleShape,
                            modifier = Modifier.size(160.dp),
                            color = Color.Gray,
                            content = {}
                        )
                        Text(
                            stringResource(id = Resources.strings.app_name.resourceId),
                            fontSize = 36.sp,
                            modifier = Modifier.padding(
                                bottom = 30.dp
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            LaunchedEffect(true) {
                visible = true
                delay(1200)
                visible = false
                delay(2500)
                onComplete()
            }
        }
    )
}

@Composable
fun StartSplashScreenPreview(onComplete: () -> Unit) {
    StartSplashScreen {}
}