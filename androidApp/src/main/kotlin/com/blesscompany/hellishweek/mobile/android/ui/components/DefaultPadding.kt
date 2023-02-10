package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.defaultPadding(value: Dp = 16.dp): Modifier {
    return this.padding(value)
}

fun Modifier.horizontalPadding(value: Dp = 16.dp): Modifier {
    return this.padding(horizontal = value)
}

fun Modifier.verticalPadding(value: Dp = 16.dp): Modifier {
    return this.padding(vertical = value)
}