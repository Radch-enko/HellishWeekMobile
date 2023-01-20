package com.blesscompany.hellishweek.mobile.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blesscompany.hellishweek.common.utils.toPrettyString
import com.blesscompany.hellishweek.mobile.android.ui.Mercury
import com.blesscompany.hellishweek.mobile.android.ui.YourPink
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickerTextField(
    value: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    placeholder: String,
    errorMessage: String?
) {
    var pickerVisible by remember { mutableStateOf(false) }

    DatePickerDialog(
        visible = pickerVisible,
        onCloseRequest = { pickerVisible = false },
        onDateSelected = onDateSelected
    )

    val isError = !errorMessage.isNullOrBlank()

    Column {
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
                        .noRippleClickable { pickerVisible = true },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AnimatedContent(
                        targetState = value?.toPrettyString() ?: placeholder
                    ) {
                        MediumAlphaText(
                            text = it,
                            style = MaterialTheme.typography.body2,
                            level = if (it == placeholder) ContentAlpha.medium else ContentAlpha.high
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp, 20.dp),
                        tint = MaterialTheme.colors.onSurface
                    )
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

@Composable
private fun DatePickerDialog(
    visible: Boolean,
    onCloseRequest: (SheetState.() -> Unit)? = null,
    onDateSelected: (LocalDate) -> Unit,
) {
    val state = rememberSheetState(visible = false, onCloseRequest = onCloseRequest)

    DateTimeDialog(
        state = state,
        selection = DateTimeSelection.Date { newDate ->
            onDateSelected(newDate.toKotlinLocalDate())
        }
    )

    if (visible) state.show() else state.hide()
}
