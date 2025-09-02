package com.vanyscore.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vanyscore.tasks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSectionsScreen() {
    val sections = remember {
        mutableStateListOf(
            "Meeting Notes",
            "Project Ideas",
            "Shopping List",
            "Travel Plans",
            "Learning Goals",
            "Daily Journal",
            "Recipe Collection",
            "Book Summaries",
            "Workout Routine",
            "Creative Writing"
        )
    }
    val addSectionDialogState = remember {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()
    return Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Заметки", style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp
                ))
            }, colors = TopAppBarDefaults .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ), actions = {
                IconButton(onClick = {
                    addSectionDialogState.value = true
                }) {
                    Icon(Icons.Default.Add,
                        contentDescription = "add_note_section",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(0.dp, padding.calculateTopPadding(), 0.dp, 0.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            sections.map { section ->
                NoteSection(title = section) { }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.surface))
            }
        }
        if (addSectionDialogState.value) {
            AddNoteSectionDialog(
                onDismiss = {
                    addSectionDialogState.value = false
                }
            ) { newSectionTitle ->
                sections.add(newSectionTitle)
            }
        }
    }
}

@Composable
fun AddNoteSectionDialog(onDismiss: () -> Unit, onResult: (String) -> Unit) {
    return Dialog(
        onDismissRequest = onDismiss,
    ) {
        val text = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
                .padding(18.dp)
        ) {
            Text(stringResource(R.string.new_note_section),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                text.value,
                modifier = Modifier.height(48.dp),
                onValueChange = { newText ->
                    text.value = newText
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = {
                onResult.invoke(text.value)
                onDismiss()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
                ),
                enabled = text.value.isNotEmpty()) {
                    Text(stringResource(R.string.add), style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp
                    ))
            }
        }
    }
}