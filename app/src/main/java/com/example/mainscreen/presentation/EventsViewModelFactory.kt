package com.example.mainscreen.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainscreen.data.EventsRepository
import com.example.mainscreen.presentation.EventsViewModel
import com.example.mainscreen.presentation.TimeViewModel

class EventsViewModelFactory(
    private val context: Context,
    private val timeViewModel: TimeViewModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            return EventsViewModel(EventsRepository(context), timeViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}