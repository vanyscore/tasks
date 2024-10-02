package com.vanyscore.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanyscore.app.AppState
import com.vanyscore.app.Services
import com.vanyscore.notes.data.INoteRepo
import com.vanyscore.notes.domain.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

data class NotesState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
)

class NotesViewModel(
    private val repo: INoteRepo = Services.notesRepo
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    private var _currentDate: Date? = null

    init {
        viewModelScope.launch {
            AppState.source.collect { appState ->
                _currentDate = appState.date
                refresh()
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val state = _state.value
            _state.update {
                state.copy(
                    isLoading = true
                )
            }
            val currentDate = this@NotesViewModel._currentDate ?: Calendar.getInstance().time
            val notes = repo.getNotes(currentDate)
            _state.update {
                state.copy(
                    notes = notes,
                    isLoading = false
                )
            }
        }
    }
}