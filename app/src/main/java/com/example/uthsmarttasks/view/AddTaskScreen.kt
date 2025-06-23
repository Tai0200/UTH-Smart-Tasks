package com.example.uthsmarttasks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.TaskEntity
import com.example.uthsmarttasks.viewmodel.TaskViewModel

@Composable
fun AddTaskScreen(navController: NavController, viewModel: TaskViewModel) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .offset(y = 24.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color = Color(0xFF2196F3), shape = CircleShape)
                    .clickable {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Add New",
                fontSize = 25.sp,
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Task", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Do homework") },
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { Text("Don’t give up") },
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                val task = TaskEntity(
                    title = taskTitle,
                    description = taskDescription
                )
                viewModel.addTask(task)
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Add", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
