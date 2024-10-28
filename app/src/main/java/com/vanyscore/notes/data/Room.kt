package com.vanyscore.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.vanyscore.app.room.converters.DateConverter
import com.vanyscore.notes.domain.Note
import java.util.Date

@Entity(tableName = "notes")
data class NoteRoom (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    @TypeConverters(value = [DateConverter::class])
    val created: Date,
    @TypeConverters(value = [DateConverter::class])
    val edited: Date
)

fun NoteRoom.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        created = created,
        edited = edited
    )
}

fun Note.toRoom(): NoteRoom {
    return NoteRoom(
        id = id,
        title = title,
        description = description,
        created = created,
        edited = edited
    )
}

@Dao
interface NotesDao {
    @Insert
    suspend fun createNote(note: NoteRoom)
    @Query("SELECT * FROM notes WHERE created > (:fromDate) & created < (:endDate)")
    suspend fun getNotes(fromDate: Date, endDate: Date): List<NoteRoom>
    @Query("SELECT * FROM notes WHERE id = (:id)")
    suspend fun getNote(id: Int): List<NoteRoom>
    @Update
    suspend fun updateNote(note: NoteRoom)
    @Delete
    suspend fun deleteNote(note: NoteRoom)
}