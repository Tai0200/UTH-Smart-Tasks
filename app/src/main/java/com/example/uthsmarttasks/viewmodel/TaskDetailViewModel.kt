package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.api.RetrofitInstance
import com.example.uthsmarttasks.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel() : ViewModel() {
    private val _taskDetail  = MutableStateFlow<Task?>(null)
    val taskDetail : StateFlow<Task?> = _taskDetail

    fun fetchTaskById(taskId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTaskById(taskId)
                val taskResponse = response.body()
                if(taskResponse?.isSuccess == true){
                    _taskDetail.value = taskResponse.data
                }
            } catch (e: Exception) {
                println("Exception555: ${e.message}")
            }
        }
    }
}
