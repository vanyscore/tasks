package com.vanyscore.app.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class SortState(
    val isExpanded: Boolean,
    val isAscending: Boolean,
    val sortType: SortType
)

enum class SortType {
    ALPHABET, DATE
}

@Composable
fun rememberSortState(): MutableState<SortState> {
    return remember {
        mutableStateOf(SortState(
            isExpanded = false,
            isAscending = true,
            sortType = SortType.ALPHABET
        ))
    }
}

@Composable
fun SortDropDown(
    state: SortState,
    onStateChanged: (SortState) -> Unit,
) {
    val isExpanded = state.isExpanded
    val isAscending = state.isAscending
    val sortType = state.sortType
    val sortTypeIcon = if (!isAscending) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
    DropdownMenu(expanded = isExpanded, onDismissRequest = {
        onStateChanged(state.copy(
            isExpanded = !state.isExpanded
        ))
    }) {
        DropdownMenuItem(
            onClick = {
                onStateChanged(state.copy(
                    sortType = SortType.ALPHABET,
                    isAscending = !state.isAscending
                ))
            },
            text = {
                Row {
                    Icon(
                        sortTypeIcon,
                        contentDescription = "sort_type",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("Alphabet", color =
                        if (sortType == SortType.ALPHABET)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )
        DropdownMenuItem(
            onClick = {
                onStateChanged(state.copy(
                    sortType = SortType.DATE,
                    isAscending = !state.isAscending
                ))
            },
            text = {
                Row {
                    Icon(
                        sortTypeIcon,
                        contentDescription = "sort_type",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("Date", color =
                        if (sortType == SortType.DATE)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )
    }
}