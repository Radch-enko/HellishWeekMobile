package com.blesscompany.hellishweek.mobile.android.screens.countrysearcher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.ui.Alto
import com.blesscompany.hellishweek.mobile.android.ui.components.BackButton
import com.blesscompany.hellishweek.mobile.android.ui.components.ErrorMessage
import com.blesscompany.hellishweek.mobile.android.ui.components.defaultPadding
import com.blesscompany.hellishweek.resources.Resources
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountrySearcherScreen(
    viewModel: CountrySearcherScreenViewModel = koinViewModel(),
    onBack: (selectedCountry: String?) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val statusBarColor = MaterialTheme.colors.secondary
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.sharedFlow.collectLatest { effect ->
                when (effect) {
                    is CountrySearcherScreenViewModel.Effect.OnCountrySelected -> onBack(effect.selectedCountry)
                }
            }
        }
    }


    Surface(modifier = Modifier.fillMaxSize(), color = statusBarColor) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Scaffold(topBar = {
                SearchTopBar(
                    query = state.query,
                    placeholder = stringResource(id = Resources.strings.search.resourceId),
                    onQueryChange = {
                        viewModel.sendEvent(
                            CountrySearcherScreenViewModel.Event.OnQueryChanged(
                                it
                            )
                        )
                    },
                    onBack = { onBack(state.selectedCountry?.name) },
                )
            }) { innerPadding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .align(Alignment.TopCenter)
                        )
                    }
                    when {
                        state.errorMessage != null -> {
                            state.errorMessage?.resourceId?.let {
                                ErrorMessage(
                                    modifier = Modifier.align(Alignment.Center),
                                    value = it,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                        else -> {
                            Column(
                                modifier = Modifier.padding(innerPadding),
                                verticalArrangement = Arrangement.Center
                            ) {
                                state.countries.forEach { country ->
                                    CountryListItem(
                                        countryEntity = country
                                    ) {
                                        viewModel.sendEvent(
                                            CountrySearcherScreenViewModel.Event.OnSelectedCountry(
                                                country
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountryListItem(countryEntity: CountryEntity, onItemSelected: () -> Unit) {
    Column(modifier = Modifier.clickable { onItemSelected() }) {
        Text(
            text = countryEntity.name,
            modifier = Modifier.defaultPadding()
        )
        Divider()
    }
}

@Composable
fun SearchTopBar(
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit,
    backgroundColor: Color = Color.White
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        navigationIcon = {
            BackButton(onButtonClick = onBack, modifier = Modifier)
        },
        title = {
            SearchField(
                query = query,
                placeholder = placeholder,
                onQueryChange = onQueryChange
            )
        },
        elevation = 5.dp
    )
}

@Composable
fun SearchField(
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder, style = MaterialTheme.typography.body1, color = Alto
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = MaterialTheme.colors.secondary,
            ),
            trailingIcon = {
                if (query.isBlank()) {
                    Icon(
                        Icons.Filled.Search,
                        tint = Color.Black,
                        contentDescription = null
                    )
                } else {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(
                            Icons.Filled.Cancel,
                            tint = Color.Black,
                            contentDescription = null
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.body1
        )
    }
}
