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

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _profileState = mutableStateOf<ProfileState>(ProfileState.Loading)
    val profileState = _profileState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            try {
                val profile = repository.getProfile()
                _profileState.value = ProfileState.Success(profile)
            } catch (e: Exception) {
                _profileState.value = ProfileState.Error("Ошибка загрузки профиля")
            }
        }
    }

    fun updateProfile(username: String, avatarResId: Int) {
        viewModelScope.launch {
            val updatedProfile = repository.updateProfile(username, avatarResId)
            _profileState.value = ProfileState.Success(updatedProfile)
        }
    }

    fun updateStats(newStats: Map<String, Int>) {
        viewModelScope.launch {
            val currentProfile = (profileState.value as? ProfileState.Success)?.profile
            if (currentProfile != null) {
                val updatedProfile = repository.updateProfile(
                    username = currentProfile.username,
                    avatarResId = currentProfile.avatarResId,
                    stats = newStats
                )
                _profileState.value = ProfileState.Success(updatedProfile)
            }
        }
    }

    fun updateAchievements(newAchievements: List<Achievement>) {
        viewModelScope.launch {
            val currentProfile = (profileState.value as? ProfileState.Success)?.profile
            if (currentProfile != null) {
                val updatedProfile = repository.updateProfile(
                    username = currentProfile.username,
                    avatarResId = currentProfile.avatarResId,
                    achievements = newAchievements
                )
                _profileState.value = ProfileState.Success(updatedProfile)
            }
        }
    }
}

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val profile: Profile) : ProfileState()
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