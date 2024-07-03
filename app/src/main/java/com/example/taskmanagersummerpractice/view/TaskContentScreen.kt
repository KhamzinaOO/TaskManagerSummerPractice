package com.example.taskmanagersummerpractice.view

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagersummerpractice.R
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.viewmodel.TaskViewModel
import com.example.taskmanagersummerpractice.viewmodel.TaskViewModelFactory

@Composable
fun TaskContentScreen(
    application: Application,
                task : Task
){

    val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(application))

    var taskTitle by remember {mutableStateOf(task.title)}
    var taskContent by remember {mutableStateOf(task.content)}

    Column(
        modifier = Modifier
            .padding(8.dp, 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.nameText))
        OutlinedTextField(
            value = taskTitle,
            onValueChange = {taskTitle=it},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = taskContent,
            onValueChange ={taskContent=it},
            singleLine = false,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(400.dp)
                .verticalScroll(rememberScrollState())
        )
        Button(onClick = {taskViewModel.addOrAlterTask(task.taskId, taskTitle, taskContent)}) {
            Text(stringResource(R.string.saveText))
        }
    }
}