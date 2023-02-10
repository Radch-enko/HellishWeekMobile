package com.blesscompany.hellishweek.mobile.android.screens.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.R
import com.blesscompany.hellishweek.mobile.android.ui.components.AlternativeButton
import com.blesscompany.hellishweek.mobile.android.ui.components.DefaultButton
import com.blesscompany.hellishweek.resources.Resources
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun PreviewScreen(goToRegistration: () -> Unit, goToAuthorization: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AboutSlider(
            modifier = Modifier.padding(top = 120.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlternativeButton(
                text = stringResource(id = Resources.strings.registration.resourceId),
                onClick = goToRegistration
            )
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(
                text = stringResource(id = Resources.strings.sign_in.resourceId),
                onClick = goToAuthorization,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AboutSlider(modifier: Modifier) {
    val pagerState = rememberPagerState()
    Column(modifier = modifier) {
        HorizontalPager(count = 3, state = pagerState) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.slider_example_image_1),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = Resources.strings.improve_yourself.resourceId),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(id = Resources.strings.improve_yourself_desc.resourceId),
                        modifier = Modifier.fillMaxWidth(.5f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }
}
