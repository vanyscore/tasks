package com.vanyscore.notes.data

import com.vanyscore.app.domain.EventBus
import com.vanyscore.app.utils.DateUtils
import com.vanyscore.notes.domain.Note
import java.util.Date

class NoteRepoRoom(
    private val dao: NotesDao
) : INoteRepo {

    override suspend fun createNote(note: Note) {
        dao.createNote(note.toRoom())
        EventBus.triggerNotesUpdated()
    }

    override suspend fun getNotes(fromDate: Date, toDate: Date): List<Note> {
        val notes = dao.getNotes(fromDate, toDate).map { it.toDomain() }
        return notes.filter {
            DateUtils.isDateEqualsByDay(it.created, fromDate)
        }
    }

    override suspend fun getNote(id: Int): Note? {
        val note = dao.getNote(id).firstOrNull()?.toDomain()
        return note
    }

    override suspend fun updateNote(note: Note): Boolean {
        dao.updateNote(note.toRoom())
        EventBus.triggerNotesUpdated()
        return true
    }

    override suspend fun deleteNote(note: Note): Boolean {
        dao.deleteNote(note.toRoom())
        EventBus.triggerNotesUpdated()
        return true
    }
}