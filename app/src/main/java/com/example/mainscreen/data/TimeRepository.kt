package com.example.mainscreen.data

import com.example.mainscreen.data.TimeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiService {
    @GET("timezone")
    suspend fun getCurrentTime(@Query("timezone") timezone: String): TimeResponse
}

object TimeRepository {
    private val apiService: TimeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://worldtimeapi.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(TimeApiService::class.java)
    }

    suspend fun getCurrentTime(timezone: String): Result<TimeResponse> {
        return try {
            val response = apiService.getCurrentTime(timezone)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}