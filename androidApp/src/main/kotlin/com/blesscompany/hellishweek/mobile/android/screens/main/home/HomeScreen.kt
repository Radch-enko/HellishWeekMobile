package com.blesscompany.hellishweek.mobile.android.screens.main.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.features.home.ChallengeCardUI
import com.blesscompany.hellishweek.mobile.android.ui.Boulder
import com.blesscompany.hellishweek.mobile.android.ui.GunPowder
import com.blesscompany.hellishweek.mobile.android.ui.components.MediumAlphaText
import com.blesscompany.hellishweek.mobile.android.ui.components.VerticalSpacer
import com.blesscompany.hellishweek.resources.Resources
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = koinViewModel(), openNotifications: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val queryState by viewModel.queryState.collectAsState()

    val primaryColor = MaterialTheme.colors.primary
    val activity = (LocalContext.current as ComponentActivity)
    activity.window.statusBarColor = primaryColor.toArgb()

    val appBarHeight = 56.dp
    val appBarPadding = 24.dp

    Scaffold(modifier = Modifier.systemBarsPadding(), topBar = {
        Column {
            TopAppBar(
                modifier = Modifier.height(appBarHeight + appBarPadding),
                title = {
                    Box(modifier = Modifier.padding(vertical = 12.dp)) {
                        SearchField(
                            query = queryState,
                            placeholder = "Search...",
                            onQueryChange = {
                                viewModel.sendEvent(
                                    HomeScreenViewModel.Event.OnQueryChanged(
                                        it
                                    )
                                )
                            })
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(8.dp)) {
                        NotificationIndicator(openNotifications)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        }
    }) { innerPadding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            ChallengesList(modifier = Modifier.padding(innerPadding), items = state.challenges)
        }
    }
}

@Composable
fun ChallengesListTitle(modifier: Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(id = Resources.strings.train_your_willpower.resourceId),
        style = typography.h4
    )
}

@Composable
fun NotificationIndicator(openNotifications: () -> Unit) {
    IconButton(onClick = openNotifications) {
        Icon(Icons.Default.Notifications, contentDescription = null)
    }
}

@Composable
private fun ChallengesList(modifier: Modifier, items: List<ChallengeCardUI>) {
    LazyColumn(modifier = modifier) {
        item {
            ChallengesListTitle(modifier = Modifier.padding(16.dp))
        }
        items(items) { item ->
            VerticalSpacer()
            ChallengeListItem(item)
        }
        item {
            VerticalSpacer()
        }
    }
}

@Composable
fun ChallengeListItem(item: ChallengeCardUI) {
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Surface(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(percent = 10)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = item.title, style = typography.h5)
                VerticalSpacer()
                MediumAlphaText(
                    text = item.description,
                    style = typography.body1,
                    textAlign = TextAlign.Start
                )
                VerticalSpacer()

                val startAnnotatedString: AnnotatedString = buildAnnotatedString {
                    val str = stringResource(
                        id = Resources.strings.challenge_card_start_date.resourceId,
                        item.startDate
                    )
                    val startIndex = str.indexOf(":") + 1
                    val end = str.lastIndex + 1
                    append(str)
                    addStyle(
                        style = SpanStyle(
                            color = Boulder, textDecoration = TextDecoration.None
                        ), start = startIndex, end = end
                    )
                }

                val endAnnotatedString: AnnotatedString = buildAnnotatedString {
                    val str = stringResource(
                        id = Resources.strings.challenge_card_end_date.resourceId,
                        item.endDate
                    )
                    val startIndex = str.indexOf(":") + 1
                    val end = str.lastIndex + 1
                    append(str)
                    addStyle(
                        style = SpanStyle(
                            color = Boulder, textDecoration = TextDecoration.None
                        ), start = startIndex, end = end
                    )
                }

                Text(text = startAnnotatedString)
                VerticalSpacer(5.dp)
                Text(text = endAnnotatedString)
            }
        }
    }
}

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder, style = typography.body1, color = GunPowder
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = MaterialTheme.colors.secondary,
            textColor = GunPowder
        ),
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        Icons.Filled.Cancel,
                        tint = GunPowder,
                        contentDescription = null,
                    )
                }
            }
        },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                tint = GunPowder,
                contentDescription = null,
            )
        },
        textStyle = typography.body1,
        shape = RoundedCornerShape(percent = 25)
    )

}
