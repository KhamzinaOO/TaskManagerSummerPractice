package com.example.taskmanagersummerpractice.model.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class TagsFromTask(
    @Embedded val tag : TagsDbEntity,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "taskId",
        associateBy = Junction(TaskTagsCrossRefDbEntity::class)
    )
    val tasks: List<TasksDbEntity>
)