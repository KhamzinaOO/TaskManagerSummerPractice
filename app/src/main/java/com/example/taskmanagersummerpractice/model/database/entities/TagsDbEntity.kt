package com.example.taskmanagersummerpractice.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("TaskTags")
class TagsDbEntity (
    @PrimaryKey val tagId : Int,
    @ColumnInfo var tagName : String
)
