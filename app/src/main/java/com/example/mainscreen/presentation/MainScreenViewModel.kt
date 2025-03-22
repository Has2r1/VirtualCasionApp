package com.example.mainscreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mainscreen.data.Event
import com.example.mainscreen.data.EventsRepository
import com.example.mainscreen.data.MultiplierRepository

data class ActiveEvent(
    val event: Event,
    val progress: Int,
    val timeRemaining: String
)

class MainScreenViewModel(
    private val multiplierRepository: MultiplierRepository,
    private val eventsRepository: EventsRepository,
    private val timeViewModel: TimeViewModel
) : ViewModel() {
    private val _multiplier = mutableStateOf(1.1f)
    val multiplier: State<Float> = _multiplier

    private val _daysUntilReset = mutableStateOf(0)
    val daysUntilReset: State<Int> = _daysUntilReset

    private val _activeEvents = mutableStateOf<List<ActiveEvent>>(emptyList())
    val activeEvents: State<List<ActiveEvent>> = _activeEvents

    private var lastFetchedTime: Long? = null
    private var lastFetchLocalTime: Long? = null

    init {
        updateMultiplier()
        updateActiveEvents()
    }

    fun updateMultiplier() {
        multiplierRepository.updateMultiplier()
        _multiplier.value = multiplierRepository.getMultiplier()
        _daysUntilReset.value = multiplierRepository.getDaysUntilReset()
    }

    fun updateActiveEvents() {
        val currentLocalTime = System.currentTimeMillis() / 1000
        if (lastFetchedTime == null || (currentLocalTime - (lastFetchLocalTime ?: 0)) >= 60) {
            timeViewModel.fetchCurrentTime("UTC")
            val timeState = timeViewModel.timeState.value
            if (timeState is TimeState.Success) {
                lastFetchedTime = timeViewModel.currentTime.value
                lastFetchLocalTime = currentLocalTime
            }
        }

        val currentTime = if (lastFetchedTime != null && lastFetchLocalTime != null) {
            lastFetchedTime!! + (currentLocalTime - lastFetchLocalTime!!)
        } else {
            timeViewModel.currentTime.value
        }

        val events = eventsRepository.getEvents()
        val activeEvents = events
            .filter { it.startTime != null && it.endTime != null }
            .map { event ->
                val endTime = event.endTime!!
                val startTime = event.startTime!!
                val totalDuration = endTime - startTime
                val elapsedTime = currentTime - startTime
                val isCompleted = currentTime >= endTime
                val progress = if (isCompleted) {
                    100
                } else if (totalDuration > 0) {
                    ((elapsedTime.toFloat() / totalDuration) * 100).coerceIn(0f, 100f).toInt()
                } else {
                    0
                }

                val timeRemaining = if (isCompleted) {
                    "Завершено"
                } else {
                    val remainingSeconds = (endTime - currentTime).coerceAtLeast(0)
                    val hours = remainingSeconds / 3600
                    val minutes = (remainingSeconds % 3600) / 60
                    if (hours > 0) {
                        "${hours}ч. ${minutes}мин."
                    } else {
                        "${minutes}мин."
                    }
                }

                ActiveEvent(
                    event = event,
                    progress = progress,
                    timeRemaining = timeRemaining
                )
            }
            .sortedWith(compareBy({ it.event.endTime!! <= currentTime }, { it.event.endTime!! - currentTime }))
            .take(3)

        _activeEvents.value = activeEvents
    }
}