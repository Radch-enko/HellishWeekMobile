package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.blesscompany.hellishweek.mobile.android.R

@Composable
fun WavesBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.top_wave),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            painter = painterResource(id = R.drawable.bottom_wave),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}