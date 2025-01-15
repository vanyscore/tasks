package com.vanyscore.notes.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanyscore.app.Services
import com.vanyscore.app.utils.FileUtil
import com.vanyscore.app.utils.Logger
import com.vanyscore.notes.data.INoteRepo
import com.vanyscore.notes.domain.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.UUID

data class NoteState(
    val note: Note,
    val canClose: Boolean = false,
)

class NoteViewModel(
    private val repo: INoteRepo = Services.notesRepo
) : ViewModel() {

    private var noteId: Int? = null

    private val _state = MutableStateFlow(NoteState(
        note = Note()
    ))
    val state = _state.asStateFlow()

    private fun refresh() {
        val noteId = this.noteId ?: return
        viewModelScope.launch {
            val note = repo.getNote(noteId) ?: return@launch
            _state.update {
                it.copy(
                    note = note
                )
            }
        }
    }


    fun applyNoteId(noteId: Int) {
        this.noteId = noteId

        refresh()
    }

    fun updateNote(copy: Note) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    note = copy
                )
            }
        }
    }

    fun attachment(context: Context, uri: Uri, note: Note) {
        // save uri to local
        val attachmentUUID = UUID.randomUUID()
        val attachmentFileExtension = FileUtil.getFileExtensionFromUri(context, uri)
        val fileName = "${attachmentUUID}.$attachmentFileExtension"
        val savedFileUri = FileUtil.saveFileToInternalStorage(context, uri, fileName) ?: return
        Logger.log("Save attachment uri: $savedFileUri")
        updateNote(note.copy(
            images = note.images.toMutableList().apply {
                add(savedFileUri)
            }
        ))
    }

    fun saveNote(forDate: Date) {
        viewModelScope.launch {
            val note = _state.value.note
            val currentDate = Calendar.getInstance().time
            if (note.id == null) {
                repo.createNote(note.copy(
                    created = forDate,
                    edited = forDate
                ))
            } else {
                repo.updateNote(note.copy(
                    edited = currentDate
                ))
            }
            _state.update {
                it.copy(
                    canClose = true
                )
            }
        }
    }

    fun removeNote(note: Note) {
        if (note.id != null) {
            viewModelScope.launch {
                repo.deleteNote(note)
                _state.update {
                    it.copy(
                        canClose = true
                    )
                }
            }
        }
    }
}