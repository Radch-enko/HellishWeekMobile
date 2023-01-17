package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.ui.Mercury
import com.blesscompany.hellishweek.mobile.android.ui.YourPink

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> DropDownTextField(
    text: String,
    possibleValues: List<T>,
    placeholder: String,
    errorMessage: String? = null,
    onSelected: (T) -> Unit
) {
    var genderMenuExpanded by remember { mutableStateOf(false) }
    val isError = !errorMessage.isNullOrBlank()

    Surface(
        modifier = Modifier.defaultMinSize(
            minWidth = TextFieldDefaults.MinWidth,
            minHeight = TextFieldDefaults.MinHeight
        ), color = if (isError) YourPink else Mercury, shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                top = 3.dp,
                start = 16.dp,
                bottom = 4.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable { genderMenuExpanded = true },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedContent(targetState = text.ifEmpty { placeholder }) {
                    Text(text = it, style = MaterialTheme.typography.body2)
                }
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        if (genderMenuExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )

                    DropdownMenu(
                        expanded = genderMenuExpanded,
                        onDismissRequest = { genderMenuExpanded = false }
                    ) {
                        possibleValues.forEach { value ->
                            DropdownMenuItem(onClick = {
                                onSelected(value)
                                genderMenuExpanded = false
                            }) {
                                Text(value.toString())
                            }
                        }
                    }
                }
            }

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
}