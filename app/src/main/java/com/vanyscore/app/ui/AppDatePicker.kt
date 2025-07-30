package com.vanyscore.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vanyscore.app.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AppDatePicker(
    initDateMillis: Long? = Calendar.getInstance().timeInMillis,
    selectedMillis: List<Long> = emptyList(),
    maxDateMillis: Long? = null,
) {
    val currentDt = remember { mutableStateOf(Calendar.getInstance().time) }
    Surface(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MonthSelect(dt = currentDt)
            Days(dt = currentDt)
        }
    }
}

@Composable
fun MonthSelect(dt: MutableState<Date>) {
    val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
    return Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                dt.value = Calendar.getInstance().apply {
                    time = dt.value
                    set(Calendar.MONTH, get(Calendar.MONTH) - 1)
                }.time
            }
        ) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "prevDate")
        }
        Text(dateFormat.format(dt.value), style = TextStyle(
            fontWeight = FontWeight.Bold
        ))
        IconButton(
            onClick = {
                dt.value = Calendar.getInstance().apply {
                    time = dt.value
                    set(Calendar.MONTH, get(Calendar.MONTH) + 1)
                }.time
            }
        ) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "nextDate")
        }
    }
}

@Composable
fun Days(dt: MutableState<Date>) {
    val days = Calendar.getInstance().apply {
        time = dt.value
    }.getMaximum(Calendar.DAY_OF_MONTH)
    val rows = days / 7
    val firstDt = Calendar.getInstance().apply {
        time = dt.value
    }
    while (firstDt.get(Calendar.DAY_OF_MONTH) != 1) {
        firstDt.set(Calendar.DAY_OF_YEAR, firstDt.get(Calendar.DAY_OF_YEAR) - 1)
    }
    while (firstDt.get(Calendar.DAY_OF_WEEK) != 1) {
        firstDt.set(Calendar.DAY_OF_YEAR, firstDt.get(Calendar.DAY_OF_YEAR) - 1)
    }
    val firstDtTime = firstDt.time
    val formatter = SimpleDateFormat("dd", Locale.getDefault())
    return Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 0 until 5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (k in 1 ..7) {
                    val showDt = firstDt.apply {
                        time = firstDtTime
                        set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) + (i * 7 + k))
                    }.time
                    val isCurrentDt = DateUtils.isDateEqualsByDay(showDt, dt.value)
                    val isCurrentMonth = Calendar.getInstance().apply {
                        time = showDt
                    }.get(Calendar.MONTH) == Calendar.getInstance().apply {
                        time = dt.value
                    }.get(Calendar.MONTH)
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(4.dp),
                        color = if (isCurrentDt) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                    ) {
                        Text(formatter.format(showDt), style = TextStyle(
                            color = if (isCurrentMonth) Color.Unspecified else MaterialTheme.colorScheme.scrim
                        ))
                    }
                }
            }
        }
    }
}

