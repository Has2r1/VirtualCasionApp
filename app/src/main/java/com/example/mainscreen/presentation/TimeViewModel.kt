package com.example.mainscreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mainscreen.data.TimeRepository
import com.example.mainscreen.data.TimeResponse
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class TimeViewModel(private val repository: TimeRepository) : ViewModel() {
    private val _timeState = mutableStateOf<TimeState>(TimeState.Loading)
    val timeState: State<TimeState> = _timeState

    private val _currentTime = mutableStateOf(System.currentTimeMillis() / 1000)
    val currentTime: State<Long> = _currentTime

    fun fetchCurrentTime(timezone: String) {
        viewModelScope.launch {
            _timeState.value = TimeState.Loading
            val result = repository.getCurrentTime(timezone)
            if (result.isSuccess) {
                result.getOrNull()?.let { timeResponse ->
                    if (timeResponse.unixtime == 0L) {
                        val localUnixTime = System.currentTimeMillis() / 1000
                        _currentTime.value = localUnixTime
                        _timeState.value = TimeState.Success(
                            TimeResponse(
                                currentLocalTime = ZonedDateTime.now().toString(),
                                timeZone = timezone
                            )
                        )
                        println("Failed to parse unixtime, using local time: $localUnixTime")
                    } else {
                        // println("Time fetched successfully: ${timeResponse.unixtime}")
                        _currentTime.value = timeResponse.unixtime
                        _timeState.value = TimeState.Success(timeResponse)
                    }
                } ?: run {
                    // Fallback на локальное время, если API вернул null
                    val localUnixTime = System.currentTimeMillis() / 1000
                    _currentTime.value = localUnixTime
                    _timeState.value = TimeState.Success(
                        TimeResponse(
                            currentLocalTime = ZonedDateTime.now().toString(),
                            timeZone = timezone
                        )
                    )
                    println("API returned null, using local time: $localUnixTime")
                }
            } else {
                // Fallback на локальное время в случае ошибки
                val localUnixTime = System.currentTimeMillis() / 1000
                _currentTime.value = localUnixTime
                _timeState.value = TimeState.Success(
                    TimeResponse(
                        currentLocalTime = ZonedDateTime.now().toString(),
                        timeZone = timezone
                    )
                )
                println("Time fetch error: ${result.exceptionOrNull()?.message}, using local time: $localUnixTime")
            }
        }
    }
}

sealed class TimeState {
    object Loading : TimeState()
    data class Success(val timeResponse: TimeResponse) : TimeState()
    data class Error(val message: String) : TimeState()
}