package com.example.taskmanagersummerpractice.model.database.entities

import androidx.room.Entity

@Entity(primaryKeys =["taskId","tagId"])
data class TaskTagsCrossRefDbEntity (
    val taskId : Int,
    val tagId : Int
)