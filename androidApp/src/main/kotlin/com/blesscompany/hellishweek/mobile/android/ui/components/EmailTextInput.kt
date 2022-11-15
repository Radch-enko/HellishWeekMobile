package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    TextFieldDefault(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}