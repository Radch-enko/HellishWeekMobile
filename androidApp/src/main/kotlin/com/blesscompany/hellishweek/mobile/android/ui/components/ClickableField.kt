package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.mobile.android.ui.Mercury
import com.blesscompany.hellishweek.mobile.android.ui.YourPink

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClickableField(
    value: String?,
    onClick: () -> Unit,
    placeholder: String,
    errorMessage: String?
) {
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
                    .noRippleClickable(onClick),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedContent(
                    targetState = value?.ifEmpty { placeholder } ?: placeholder
                ) {
                    MediumAlphaText(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        level = if (it == placeholder) ContentAlpha.medium else ContentAlpha.high
                    )
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