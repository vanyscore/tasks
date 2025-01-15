package com.vanyscore.app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vanyscore.app.room.AppDatabase
import com.vanyscore.notes.data.INoteRepo
import com.vanyscore.notes.data.NoteRepoInMemory
import com.vanyscore.notes.data.NoteRepoRoom
import com.vanyscore.tasks.data.ITaskRepo
import com.vanyscore.tasks.data.TaskRepoInMemory
import com.vanyscore.tasks.data.TaskRepoRoom

object Services {
    val tasksRepo: ITaskRepo by lazy {
        TaskRepoRoom(
            database.tasksDao()
        )
    }
    val notesRepo: INoteRepo by lazy {
        NoteRepoRoom(database.notesDao())
    }

    private lateinit var database: AppDatabase

    fun bindContext(appContext: Context) {
//        database = Room.inMemoryDatabaseBuilder(
//            context = appContext,
//            AppDatabase::class.java,
//        ).build()
        database = Room.databaseBuilder(
            context = appContext,
            AppDatabase::class.java, "tn_database"
        ).build()
    }
}