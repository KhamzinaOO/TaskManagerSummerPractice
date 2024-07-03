package com.example.taskmanagersummerpractice.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class TasksDbEntity(
    @PrimaryKey val taskId : Int,
    @ColumnInfo var title : String,
    @ColumnInfo var content : String,
    @ColumnInfo var isEditing : Boolean = false
)