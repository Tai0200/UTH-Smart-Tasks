package com.example.uthsmarttasks.api

import com.example.uthsmarttasks.model.TaskResponse
import com.example.uthsmarttasks.model.TaskResponse2
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface ApiService{
    @GET("researchUTH/tasks")
    suspend fun getTasks(): Response<TaskResponse>

    @GET("researchUTH/task/{id}")
    suspend fun getTaskById(@Path("id") id : Int) : Response<TaskResponse2>
}