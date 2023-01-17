package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.ui.Mercury
import com.blesscompany.hellishweek.mobile.android.ui.YourPink

@Composable
fun TextFieldDefault(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String? = null,
    enabled: Boolean = true,
) {
    val isError = !errorMessage.isNullOrBlank()
    val error: @Composable (() -> Unit) = {
        Icon(
            Icons.Filled.Error,
            "error",
            tint = MaterialTheme.colors.error
        )
    }

    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            placeholder = {
                Text(
                    text = placeholder, style = MaterialTheme.typography.body2
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isError) YourPink else Mercury,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = visualTransformation,
            trailingIcon = if (isError) error else trailingIcon,
            keyboardOptions = keyboardOptions,
            enabled = enabled
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}