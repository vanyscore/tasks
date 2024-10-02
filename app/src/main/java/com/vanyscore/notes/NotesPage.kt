package com.vanyscore.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vanyscore.notes.viewmodel.NotesViewModel

@Composable
fun NotesPage(
    viewModel: NotesViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    val notes = state.notes
    return Scaffold {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    notes
                ) { note ->
                    NoteItem(
                        note = note,
                        onClick = {}
                    )
                }
            }
        }
    }
}