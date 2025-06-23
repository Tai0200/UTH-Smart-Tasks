package com.example.uthsmarttasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.api.RetrofitInstance
import com.example.uthsmarttasks.model.TaskDatabase
import com.example.uthsmarttasks.model.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(application).taskDao()

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    init {
        fetchTasksFromRoom()
        fetchTasksFromApiIfEmpty() // chỉ gọi khi Room rỗng
    }


    private fun fetchTasksFromRoom() {
        viewModelScope.launch {
            val taskList = taskDao.getAllTasks().first()
            _tasks.value = taskList.reversed() // Hiển thị task mới nhất lên đầu
        }
    }

    private fun fetchTasksFromApiIfEmpty() {
        viewModelScope.launch {
            val localTasks = taskDao.getAllTasks().first()
            if (localTasks.isEmpty()) {
                try {
                    val response = RetrofitInstance.api.getTasks()
                    if (response.isSuccessful) {
                        val taskResponse = response.body()
                        if (taskResponse?.isSuccess == true) {
                            taskResponse.data.forEach { task ->
                                taskDao.insertTask(
                                    TaskEntity(
                                        title = task.title,
                                        description = task.description,
                                        priority = task.priority,
                                        status = task.status,
                                        dueDate = task.dueDate
                                    )
                                )
                            }
                            fetchTasksFromRoom() // cập nhật lại danh sách
                        }
                    }
                } catch (e: Exception) {
                    println("Error fetching tasks from API: ${e.message}")
                }
            }
        }
    }


    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.insertTask(task)

            // Thêm task mới nhất lên đầu
            val updatedList = taskDao.getAllTasks().first().reversed()
            _tasks.value = updatedList
        }
    }
}
