package com.example.mainscreen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mainscreen.data.Event
import com.example.mainscreen.data.EventsRepository

class EventsViewModel(
    private val repository: EventsRepository,
    private val timeViewModel: TimeViewModel
) : ViewModel() {
    private val _events = mutableStateOf<List<Event>>(emptyList())
    val events: State<List<Event>> = _events

    init {
        loadEvents()
    }

    fun loadEvents() {
        timeViewModel.fetchCurrentTime("UTC")
        _events.value = repository.getEvents()
    }

    fun updateEvent(updatedEvent: Event) {
        val updatedList = _events.value.map { if (it.title == updatedEvent.title) updatedEvent else it }
        _events.value = updatedList
        repository.saveEvents(updatedList)
    }
}