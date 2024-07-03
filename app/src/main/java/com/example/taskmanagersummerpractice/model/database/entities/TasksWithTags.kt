package com.example.taskmanagersummerpractice.model.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class TasksWithTags(
    @Embedded val task : TasksDbEntity,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "tagId",
        associateBy = Junction(TaskTagsCrossRefDbEntity::class)
    )
    val tags: List<TagsDbEntity>
)