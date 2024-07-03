package com.example.taskmanagersummerpractice.view

sealed class Screen (val route: String){
    data object TaskContentScreen:Screen("taskcontentscreen")
    data object TaskListScreen:Screen("tasklistscreen")
}