package com.example.uthsmarttasks.model

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    fun getAllTasks() = taskDao.getAllTasks()
}
