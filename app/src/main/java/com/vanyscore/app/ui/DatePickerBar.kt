package com.vanyscore.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanyscore.app.AppState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBar() {
    val yearFormat = remember {
        SimpleDateFormat("yyyy", Locale.getDefault())
    }
    val monthFormat = remember {
        SimpleDateFormat("MMMM", Locale.getDefault())
    }
    val dayFormat = remember {
        SimpleDateFormat("dd", Locale.getDefault())
    }
    val state = AppState.source.collectAsState()
    val currentDate = state.value.date
    val textStyle = TextStyle(fontSize = 16.sp, color = Color.White)
    val dialogState = remember {
        mutableStateOf(false)
    }
    val appState = state.value
    return TopAppBar(
        modifier = Modifier
            .height(56.dp),
        title = {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
                    .clickable {
                        dialogState.value = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            AppState.updateState(appState.copy(
                                date = Calendar.getInstance().apply {
                                    time = currentDate
                                    add(Calendar.DAY_OF_MONTH, -1)
                                }.time
                            ))
                        },
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft, "Left", tint = Color.White
                        )
                    }
                    Text(dayFormat.format(currentDate), style = textStyle)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(monthFormat.format(currentDate), style = textStyle)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(yearFormat.format(currentDate), style = textStyle)
                    IconButton(
                        onClick = {
                            AppState.updateState(appState.copy(
                                date = Calendar.getInstance().apply {
                                    time = currentDate
                                    add(Calendar.DAY_OF_MONTH, 1)
                                }.time
                            ))
                        }
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight, "Right", tint = Color.White
                        )
                    }
                }
            }
            if (dialogState.value) {
                val datePickerState = rememberDatePickerState(
                    initialDisplayedMonthMillis = state.value.date.time,
                )
                DatePickerDialog(
                    onDismissRequest = {
                        dialogState.value = false
                    },
                    confirmButton = {
                        val selectedDateInMillis = datePickerState.selectedDateMillis
                        if (selectedDateInMillis != null) {
                            dialogState.value = false
                            AppState.updateState(appState.copy(
                                date = Calendar.getInstance().apply {
                                    timeInMillis = selectedDateInMillis
                                }.time
                            ))
                        }
                    },
                ) {
                    val currDateInMillis = Calendar.getInstance().timeInMillis
                    DatePicker(state = datePickerState, dateValidator = { time ->
                        time <= currDateInMillis
                    })
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}