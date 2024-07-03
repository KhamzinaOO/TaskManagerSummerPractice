package com.example.taskmanagersummerpractice.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val taskId : Int,
    var title : String,
    var content : String,
    var isEditing : Boolean = false
):Parcelable