package com.example.uthsmarttasks.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // Thêm hoặc cập nhật một Task vào Room Database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    // Lấy tất cả các Task từ Room Database dưới dạng Flow
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>
}
