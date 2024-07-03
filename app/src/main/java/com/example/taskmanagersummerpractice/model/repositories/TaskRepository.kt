package com.example.taskmanagersummerpractice.model.repositories

import androidx.lifecycle.LiveData
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.model.database.DAO.TasksDAO
import com.example.taskmanagersummerpractice.model.database.entities.TasksDbEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(private val _taskDao: TasksDAO) {
    private val _coroutineScope = CoroutineScope(Dispatchers.Main)

    val allTasks : LiveData<List<Task>> = _taskDao.getAllTasks()

    fun deleteTaskById(id: Int){
        _coroutineScope.launch(Dispatchers.IO) {
            _taskDao.deleteTaskById(id)
        }
    }

    fun addTask(id: Int, name:String, content: String){
        _coroutineScope.launch(Dispatchers.IO) {
            _taskDao.insertTask(TasksDbEntity(id, name, content))
        }
    }

    fun updateIsEditing(taskId: Int, isEditing: Boolean) {
        _coroutineScope.launch(Dispatchers.IO) {
            _taskDao.updateIsEditing(taskId, isEditing)
        }
    }
}