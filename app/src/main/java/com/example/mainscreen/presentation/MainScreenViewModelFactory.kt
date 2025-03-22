package com.example.mainscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainscreen.data.EventsRepository
import com.example.mainscreen.data.MultiplierRepository
import com.example.mainscreen.presentation.MainScreenViewModel
import com.example.mainscreen.presentation.TimeViewModel

class MainScreenViewModelFactory(
    private val multiplierRepository: MultiplierRepository,
    private val eventsRepository: EventsRepository,
    private val timeViewModel: TimeViewModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            return MainScreenViewModel(multiplierRepository, eventsRepository, timeViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}