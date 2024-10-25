package com.vanyscore.tasks.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.vanyscore.app.room.converters.DateConverter
import java.util.Date

@Entity(tableName = "tasks")
class TaskRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    @ColumnInfo(name = "is_success")
    val isSuccess: Boolean,
    @TypeConverters(value = [DateConverter::class])
    val date: Date,
)

fun TaskRoom.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        isSuccess = isSuccess,
        date = date
    )
}

@Dao
interface TasksDao {
    @Insert
    suspend fun createTask(task: TaskRoom)

    @Query("SELECT * FROM tasks WHERE date = (:date)")
    suspend fun getTasks(date: Date): List<TaskRoom>

    @Query("SELECT * FROM tasks WHERE date > (:startDate) & date < (:endDate)")
    suspend fun getTasks(startDate: Date, endDate: Date): List<TaskRoom>

    @Update
    suspend fun updateTask(task: TaskRoom): Int

    @Delete
    suspend fun deleteTask(task: TaskRoom): Int
}