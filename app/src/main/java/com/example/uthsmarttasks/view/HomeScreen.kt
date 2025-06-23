package com.example.uthsmarttasks.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.TaskEntity
import com.example.uthsmarttasks.viewmodel.TaskViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: TaskViewModel = viewModel() // <-- Lấy ViewModel đúng cách
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = { TopNavBar() },
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                if (tasks.isEmpty()) {
                    EmptyTaskScreen()
                } else {
                    // Danh sách Task
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(tasks) { task ->
                            TaskItem(task = task, onClick = {
                                navController.navigate("detail/${task.id}")
                            })
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun TaskItem(task: TaskEntity, onClick: () -> Unit) {
    var checked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (task.priority) {
                "High" -> Color(0xFF7286F6)
                "Medium" -> Color(0xFFF57971)
                "Low" -> Color(0xFFF8EA76)
                else -> Color.White
            }
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = task.description, fontSize = 18.sp)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Status: " + task.status, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = task.dueDate, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

