package com.vanyscore.notes.data

import androidx.core.net.toUri
import com.vanyscore.app.domain.EventBus
import com.vanyscore.app.utils.DateUtils
import com.vanyscore.app.utils.FileUtil
import com.vanyscore.notes.domain.Note
import java.util.Date

class NoteRepoRoom(
    private val dao: NotesDao
) : INoteRepo {

    override suspend fun createNote(note: Note) {
        val noteId = dao.createNote(note.toRoom()).toInt()
        note.images.forEach {
            // TODO: Save uri file to local storage.
            val path = it.toString()
            dao.createImage(NoteImage(null, noteId, path))
        }
        EventBus.triggerNotesUpdated()
    }

    override suspend fun getNotes(fromDate: Date, toDate: Date): List<Note> {
        val notes = dao.getNotes(fromDate, toDate).withImages(dao)
        return notes.filter {
            DateUtils.isDateEqualsByDay(it.created, fromDate)
        }
    }

    override suspend fun getNote(id: Int): Note? {
        val note = dao.getNote(id).withImages(dao).firstOrNull()
        return note
    }

    override suspend fun updateNote(note: Note): Boolean {
        if (note.id == null) return false
        val oldNote = getNote(note.id) ?: return false
        val oldImages = oldNote.images
        val newImages = note.images
        if (newImages.size > oldImages.size) {
            val newPaths = mutableListOf<String>()
            newImages.forEach {
                if (!oldImages.contains(it)) {
                    newPaths.add(it.toString())
                }
            }
            newPaths.forEach { path ->
                dao.createImage(NoteImage(null, note.id, path))
            }

        } else if (newImages.size < oldImages.size) {
            val pathsForRemove = mutableListOf<String>()
            oldImages.forEach {
                if (!newImages.contains(it)) {
                    pathsForRemove.add(it.toString())
                }
            }
            pathsForRemove.mapNotNull {
                dao.getImageByPath(it).firstOrNull()
            }.forEach {
                FileUtil.removeFileByUri(it.path.toUri())
                dao.deleteImage(it)
            }
        }
        dao.updateNote(note.toRoom())
        EventBus.triggerNotesUpdated()
        return true
    }

    override suspend fun deleteNote(note: Note): Boolean {
        val noteId = note.id ?: return false
        val images = dao.getImagesByNote(noteId)
        images.forEach {
            FileUtil.removeFileByUri(it.path.toUri())
            dao.deleteImage(it)
        }
        dao.deleteNote(note.toRoom())
        EventBus.triggerNotesUpdated()
        return true
    }
}