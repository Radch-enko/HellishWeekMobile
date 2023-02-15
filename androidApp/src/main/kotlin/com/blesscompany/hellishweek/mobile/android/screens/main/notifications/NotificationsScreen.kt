package com.blesscompany.hellishweek.mobile.android.screens.main.notifications

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.features.notifications.presentation.NotificationCardUI
import com.blesscompany.hellishweek.mobile.android.screens.main.home.SearchField
import com.blesscompany.hellishweek.mobile.android.ui.components.BackIcon
import com.blesscompany.hellishweek.mobile.android.ui.components.MediumAlphaText
import com.blesscompany.hellishweek.mobile.android.ui.components.SearchIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationsScreen(
    viewModel: NotificationsScreenViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val queryState by viewModel.queryState.collectAsState()
    val primaryColor = MaterialTheme.colors.primary
    val activity = (LocalContext.current as ComponentActivity)
    activity.window.statusBarColor = primaryColor.toArgb()

    val appBarHeight = 56.dp
    val appBarPadding = 24.dp

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
                                    query = queryState,
                                    placeholder = "Search...",
                                    onQueryChange = {
                                        viewModel.sendEvent(
                                            NotificationsScreenViewModel.Event.OnQueryChanged(it)
                                        )
                                    })
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                NotificationsList(state.notifications, onItemClick = { })
            }
        }
    }
}

@Composable
private fun NotificationsList(notifications: List<NotificationCardUI>, onItemClick: () -> Unit) {
    LazyColumn(content = {
        items(notifications) { alert ->
            NotificationsListItem(alert, onItemClick)
            Divider()
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationsListItem(alert: NotificationCardUI, onItemClick: () -> Unit) {
    ListItem(
        modifier = Modifier,
        icon = {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black, CircleShape),
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
        text = {
            Text(text = alert.type, style = typography.body1)
        },
        overlineText = {
            MediumAlphaText(
                text = alert.author,
                style = typography.overline,
                textAlign = TextAlign.Start
            )
        },
        secondaryText = {
            MediumAlphaText(
                text = alert.description,
                style = typography.body1,
                textAlign = TextAlign.Start
            )
        }
    )
}

