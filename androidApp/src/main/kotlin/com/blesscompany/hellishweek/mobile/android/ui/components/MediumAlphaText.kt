package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MediumAlphaText(
    text: String,
    style: TextStyle = MaterialTheme.typography.body1,
    level: Float = ContentAlpha.medium,
    textAlign: TextAlign = TextAlign.Center
) {
    CompositionLocalProvider(LocalContentAlpha provides level) {
        Text(text = text, style = style, textAlign = textAlign)
    }
}