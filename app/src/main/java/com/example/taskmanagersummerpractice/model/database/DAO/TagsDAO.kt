package com.example.taskmanagersummerpractice.model.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagersummerpractice.model.Tag
import com.example.taskmanagersummerpractice.model.database.entities.TagsFromTask
import com.example.taskmanagersummerpractice.model.database.entities.TasksWithTags
@Dao
interface TagsDAO {
    @Transaction
    @Query("SELECT  * FROM TaskTags")
    fun getAllTags() : LiveData<List<Tag>>

    @Query("SELECT  * FROM Tasks WHERE taskId = :taskId")
    fun getTasksWithTag(taskId: Int) : LiveData<List<TasksWithTags>>

    @Query("SELECT  * FROM TaskTags WHERE tagId = :tagId")
    fun getTagsFromTask(tagId: Int) : LiveData<List<TagsFromTask>>

    @Query("DELETE FROM TaskTagsCrossRefDbEntity WHERE tagId = :tagId AND taskId = :taskId")
    fun deleteTagsFromTask(tagId: Int, taskId:Int)
}