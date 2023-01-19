package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BackButton(onButtonClick: () -> Unit, modifier: Modifier) {
    IconButton(modifier = modifier, onClick = onButtonClick) {
        Icon(Icons.Default.ArrowBack, contentDescription = null)
    }
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButton(onButtonClick = { /*TODO*/ }, modifier = Modifier)
}