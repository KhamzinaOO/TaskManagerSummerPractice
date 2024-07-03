package com.example.taskmanagersummerpractice.view


import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagersummerpractice.R
import com.example.taskmanagersummerpractice.model.Task
import com.example.taskmanagersummerpractice.viewmodel.TaskViewModel
import com.example.taskmanagersummerpractice.viewmodel.TaskViewModelFactory


@Composable
fun TaskListScreen(application: Application,
                navigateToDetail: (Task) -> Unit) {
    val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(application))

    val taskList by taskViewModel.taskList.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 48.dp, 16.dp, 8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.addTaskText))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(taskList) { item ->
                if (item.isEditing) {
                    ItemEditorDialog(item = item, onEditComplete = { editingName, editingContent ->
                        taskViewModel.addOrAlterTask( item.taskId, editingName, editingContent)
                        taskViewModel.updateIsEditing(item.taskId, false)
                    })
                } else {
                    TaskListItem(
                        item = item,
                        onEditClick = {
                            taskViewModel.updateIsEditing(item.taskId, true)
                        },
                        onDeleteClick = {
                            taskViewModel.deleteTask(item.taskId)
                        },
                        navigateToDetail
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (itemName.isNotBlank() || itemContent.isNotBlank()) {
                            val newId = (taskList.maxOfOrNull { it.taskId } ?: 0) + 1
                            taskViewModel.addOrAlterTask(newId, itemName,  itemContent)
                            showDialog = false
                            itemName = ""
                            itemContent = ""
                        }
                    }) {
                        Text(stringResource(R.string.addText))
                    }
                    Button(onClick = { showDialog = false }) {
                        Text(stringResource(R.string.cancelText))
                    }
                }
            },
            title = { Text(stringResource(R.string.addTaskText) + ":") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        placeholder = { Text(stringResource(R.string.nameText)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemContent,
                        onValueChange = { itemContent = it },
                        singleLine = false,
                        minLines = 4,
                        placeholder = { Text(stringResource(R.string.hintText)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun TaskListItem(
    item: Task,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    navigateToDetail: (Task) -> Unit
) {
    var lines by remember { mutableIntStateOf(1) }
    var arrowIcon by remember { mutableStateOf(Icons.Default.KeyboardArrowDown) }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color(0xff0f4c5c)),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navigateToDetail(item)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(item.title, modifier = Modifier.padding(32.dp, 5.dp))
            Row(modifier = Modifier.padding(8.dp)) {
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(R.string.edit_button))
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete_button))
                }
                IconButton(onClick = {
                    if (lines ==1){
                        lines = 50
                        arrowIcon=Icons.Default.KeyboardArrowUp
                    }else{
                        lines = 1
                        arrowIcon=Icons.Default.KeyboardArrowDown
                    }
                }) {
                    Icon(imageVector = arrowIcon, contentDescription = "")
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = item.content,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                maxLines = lines,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ItemEditorDialog(item: Task, onEditComplete: (String, String) -> Unit) {
    var editingName by remember { mutableStateOf(item.title) }
    var editingContent by remember { mutableStateOf(item.content) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            OutlinedTextField(
                value = editingName,
                onValueChange = { editingName = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = editingContent,
                onValueChange = { editingContent = it },
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row {
                Button(onClick = {
                    isEditing = false
                    onEditComplete(editingName, editingContent)
                }) {
                    Text(stringResource(R.string.saveText))
                }
                Button(onClick = {
                    isEditing = false
                    onEditComplete(item.title, item.content)
                }) {
                    Text(stringResource(R.string.cancelText))
                }
            }
        }
    }
}