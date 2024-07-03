package com.example.taskmanagersummerpractice.view

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.viewmodel.TaskViewModel

@Composable
fun TaskManagerApp(application: Application, navController: NavHostController){

    NavHost(navController = navController, startDestination = Screen.TaskListScreen.route) {
        composable(route=Screen.TaskListScreen.route){
            TaskListScreen(application, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("task", it)
                navController.navigate(Screen.TaskContentScreen.route)
            })
        }
            composable(route=Screen.TaskContentScreen.route){
                val task = navController.previousBackStackEntry?.savedStateHandle?.get<Task>("task")
                    ?: Task(0,"", "", false)
                TaskContentScreen(application, task=task)
            }
        }

}
