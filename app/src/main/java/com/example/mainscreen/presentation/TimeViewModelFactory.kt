package com.example.mainscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainscreen.data.TimeRepository
import com.example.mainscreen.presentation.TimeViewModel

class TimeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeViewModel::class.java)) {
            return TimeViewModel(TimeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}