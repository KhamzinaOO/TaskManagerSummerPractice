package com.example.taskmanagersummerpractice.model.repositories

import androidx.lifecycle.LiveData
import com.example.taskmanagersummerpractice.model.Tag
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.model.database.DAO.TagsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagRepository(private val _tagDao : TagsDAO) {
    private  val _coroutineScope = CoroutineScope(Dispatchers.Main)

    val allTags : LiveData<List<Tag>> = _tagDao.getAllTags()

    val tasksWithTag = {
        id: Int ->
        _tagDao.getTasksWithTag(id)
    }

    val tagsFromTask = {
            id: Int ->
        _tagDao.getTagsFromTask(id)
    }

    fun deleteTag(
        tagId: Int,
        taskId:Int
    ){
        _coroutineScope.launch(Dispatchers.IO) {
            _tagDao.deleteTagsFromTask(tagId, taskId)
        }
    }
}