package com.vanyscore.app.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SortDropDown(
    isExpanded: Boolean,
    isAscending: Boolean,
    onAlphabetClick: () -> Unit,
    onDateClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val sortTypeIcon = if (isAscending) Icons.Default.ArrowDropDown else Icons.Default.KeyboardArrowUp
    DropdownMenu(expanded = isExpanded, onDismissRequest = onDismiss) {
        DropdownMenuItem(
            onClick = onAlphabetClick,
            text = {
                Row {
                    Icon(
                        sortTypeIcon,
                        contentDescription = "sort_type",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("Alphabet")
                }
            }
        )
        DropdownMenuItem(
            onClick = onDateClick,
            text = {
                Row {
                    Icon(
                        sortTypeIcon,
                        contentDescription = "sort_type",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("Date")
                }
            }
        )
    }
}