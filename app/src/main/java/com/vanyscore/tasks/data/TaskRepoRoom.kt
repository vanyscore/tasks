package com.vanyscore.tasks.data

import java.util.Date

class TaskRepoRoom(
    private val dao: TasksDao
) : ITaskRepo {


    override suspend fun createTask(task: Task) {
        dao.createTask(task.toRoom())
    }

    override suspend fun getTasks(date: Date): List<Task> {
        return dao.getTasks(date).map { it.toDomain() }
    }

    override suspend fun getTasks(startDate: Date, endDate: Date): List<Task> {
        return dao.getTasks(startDate, endDate).map { it.toDomain() }
    }

    override suspend fun updateTask(task: Task): Boolean {
        return (dao.updateTask(task.toRoom()) == 1)
    }

    override suspend fun deleteTask(task: Task): Boolean {
        return (dao.deleteTask(task.toRoom()) == 1)
    }
}