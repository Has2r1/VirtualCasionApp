package com.example.mainscreen.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mainscreen.data.Achievement
import com.example.mainscreen.data.Profile
import com.example.mainscreen.data.ProfileRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _profileState = mutableStateOf<ProfileState>(ProfileState.Loading)
    val profileState: State<ProfileState> = _profileState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        try {
            val profile = repository.getProfile()
            _profileState.value = ProfileState.Success(profile)
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to load profile: ${e.message}")
        }
    }

    fun updateProfile(username: String, avatarResId: Int) {
        try {
            repository.updateProfile(username, avatarResId)
            loadProfile()
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to update profile: ${e.message}")
        }
    }

    fun updateStats(stats: Map<String, Int>) {
        try {
            repository.updateStats(stats)
            loadProfile()
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to update stats: ${e.message}")
        }
    }

    fun updateAchievements(achievements: List<Achievement>) {
        try {
            repository.updateAchievements(achievements)
            loadProfile()
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to update achievements: ${e.message}")
        }
    }

    // Метод для обновления GoldBalance
    fun updateGoldBalance(goldBalance: Int) {
        try {
            repository.updateGoldBalance(goldBalance)
            loadProfile()
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to update gold balance: ${e.message}")
        }
    }

    // Метод для обновления DiamondBalance
    fun updateDiamondBalance(diamondBalance: Int) {
        try {
            repository.updateDiamondBalance(diamondBalance)
            loadProfile()
        } catch (e: Exception) {
            _profileState.value = ProfileState.Error("Failed to update diamond balance: ${e.message}")
        }
    }
}

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val profile: Profile) : ProfileState() {
        val username: String get() = profile.username
        val avatarResId: Int get() = profile.avatarResId
        val stats: Map<String, Int> get() = profile.stats
        val achievements: List<Achievement> get() = profile.achievements
        val goldBalance: Int get() = profile.goldBalance
        val diamondBalance: Int get() = profile.diamondBalance
    }
    data class Error(val message: String) : ProfileState()
}

class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(ProfileRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}