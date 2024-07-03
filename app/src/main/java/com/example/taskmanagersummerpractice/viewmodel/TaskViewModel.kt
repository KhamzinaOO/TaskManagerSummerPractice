package com.example.taskmanagersummerpractice.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.model.database.ROOMDatabase
import com.example.taskmanagersummerpractice.model.repositories.TaskRepository

class TaskViewModel(application: Application) : ViewModel(){
    val taskList : LiveData<List<Task>>
    private val taskRepository: TaskRepository


    init {
        val roomDb = ROOMDatabase.getInstance(application)
        val tasksDao = roomDb.tasksDAO()
        taskRepository = TaskRepository(tasksDao)
        taskList = taskRepository.allTasks
    }

    fun addOrAlterTask(id: Int, name: String,  content : String){
        taskRepository.addTask(id, name, content)
    }

    fun deleteTask(id: Int){
        taskRepository.deleteTaskById(id)
    }

    fun updateIsEditing(taskId: Int, isEditing: Boolean) {
        taskRepository.updateIsEditing(taskId, isEditing)
    }
}