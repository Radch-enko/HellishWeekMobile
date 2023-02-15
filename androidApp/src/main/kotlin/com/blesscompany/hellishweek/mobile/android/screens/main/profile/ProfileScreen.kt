package com.blesscompany.hellishweek.mobile.android.screens.main.profile

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    val primaryColor = MaterialTheme.colors.primary
    val activity = (LocalContext.current as ComponentActivity)
    activity.window.statusBarColor = Color.BLACK

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            activity.window.statusBarColor =
                primaryColor.toArgb()
        }
    })

    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(innerPadding)
        ) {
            Text(text = "Profile")
        }
    }
}