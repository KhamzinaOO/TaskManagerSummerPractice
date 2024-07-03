package com.example.taskmanagersummerpractice.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanagersummerpractice.model.Tag
import com.example.taskmanagersummerpractice.model.database.ROOMDatabase
import com.example.taskmanagersummerpractice.model.repositories.TagRepository

class TagViewModel(application: Application) : ViewModel() {
    private val tagsList : LiveData<List<Tag>>?
    private val tagRepository: TagRepository

    init {
        val roomDb = ROOMDatabase.getInstance(application)
        val tagDao = roomDb.tagsDAO()
        tagRepository = TagRepository(tagDao)
        tagsList = tagRepository.allTags
    }


}