package com.example.taskmanagersummerpractice.model.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.model.database.entities.TasksDbEntity

@Dao
interface TasksDAO {
    @Transaction
    @Query("SELECT  * FROM Tasks")
    fun getAllTasks() : LiveData<List<Task>>

    @Query("DELETE FROM Tasks WHERE taskId = :taskId")
    fun deleteTaskById(taskId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TasksDbEntity)

    @Query("UPDATE Tasks SET isEditing = :isEditing WHERE taskId = :taskId")
    fun updateIsEditing(taskId: Int, isEditing: Boolean)
}