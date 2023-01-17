package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorMessage(modifier: Modifier = Modifier, @StringRes value: Int) {
    Text(
        modifier = modifier,
        text = stringResource(id = value),
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption
    )
}