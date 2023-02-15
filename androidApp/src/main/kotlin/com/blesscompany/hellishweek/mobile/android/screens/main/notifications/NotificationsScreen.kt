package com.blesscompany.hellishweek.mobile.android.screens.main.notifications

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.screens.main.home.SearchField
import com.blesscompany.hellishweek.mobile.android.ui.components.BackIcon
import com.blesscompany.hellishweek.mobile.android.ui.components.SearchIconButton

@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    val primaryColor = MaterialTheme.colors.primary
    val activity = (LocalContext.current as ComponentActivity)
    activity.window.statusBarColor = primaryColor.toArgb()

    val appBarHeight = 56.dp
    val appBarPadding = 24.dp

    var query by remember {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.systemBarsPadding(), topBar = {
        Column {
            var isSearchBarVisible by remember {
                mutableStateOf(false)
            }
            TopAppBar(
                modifier = Modifier.height(appBarHeight + appBarPadding),
                navigationIcon = {
                    BackIcon(onBack = {
                        isSearchBarVisible = false
                        onBack()
                    })
                },
                title = {
                    Box(modifier = Modifier.padding(vertical = 12.dp)) {
                        Row(
                            modifier = Modifier.height(appBarHeight),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AnimatedVisibility(visible = !isSearchBarVisible) {
                                Text(
                                    text = "Notifications",
                                    color = MaterialTheme.colors.onPrimary,
                                    style = typography.h5
                                )
                            }
                            AnimatedVisibility(
                                visible = isSearchBarVisible,
                                enter = fadeIn() + slideInHorizontally()
                            ) {
                                SearchField(
                                    modifier = Modifier.padding(end = 16.dp),
                                    query = query,
                                    placeholder = "Search...",
                                    onQueryChange = { query = it })
                            }
                        }
                    }
                },
                actions = {
                    AnimatedVisibility(visible = !isSearchBarVisible) {
                        SearchIconButton(onClick = { isSearchBarVisible = !isSearchBarVisible })
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        }
    }) { innerPadding ->
        Text(text = "Notifications")
    }
}

