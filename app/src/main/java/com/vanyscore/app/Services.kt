package com.vanyscore.app

import com.vanyscore.notes.data.INoteRepo
import com.vanyscore.notes.data.NoteRepoInMemory
import com.vanyscore.tasks.data.ITaskRepo
import com.vanyscore.tasks.data.TaskRepoInMemory

object Services {
    val tasksRepo: ITaskRepo = TaskRepoInMemory()
    val notesRepo: INoteRepo = NoteRepoInMemory()
}