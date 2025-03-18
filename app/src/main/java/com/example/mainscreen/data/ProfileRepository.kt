package com.example.mainscreen.data

import android.content.Context
import com.example.mainscreen.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileRepository(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Ключи для сохранения данных
    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_AVATAR_RES_ID = "avatar_res_id"
        private const val KEY_STATS = "stats"
        private const val KEY_ACHIEVEMENTS = "achievements"
    }

    fun getProfile(): Profile {
        // Загружаем сохранённые данные или используем значения по умолчанию
        val username = sharedPreferences.getString(KEY_USERNAME, "Username") ?: "Username"
        val avatarResId = sharedPreferences.getInt(KEY_AVATAR_RES_ID, R.drawable.ic_avatar)

        // Загружаем статистику
        val statsJson = sharedPreferences.getString(KEY_STATS, null)
        val stats: Map<String, Int> = if (statsJson != null) {
            val type = object : TypeToken<Map<String, Int>>() {}.type
            gson.fromJson(statsJson, type)
        } else {
            mapOf(
                "Собранные коллекции:" to 10,
                "Полученные достижения:" to 10,
                "Потраченная валюта:" to 10,
                "Количество спинов:" to 10,
                "Крупнейший выигрыш:" to 10,
                "Завершенные события:" to 10,
                "Количество дней входа:" to 10
            )
        }

        // Загружаем достижения
        val achievementsJson = sharedPreferences.getString(KEY_ACHIEVEMENTS, null)
        val achievements: List<Achievement> = if (achievementsJson != null) {
            val type = object : TypeToken<List<Achievement>>() {}.type
            gson.fromJson(achievementsJson, type)
        } else {
            listOf(
                Achievement("Покоритель удачи", "Азартные и игровые достижения", R.drawable.ic_clover, 0.75f),
                Achievement("Магнат удачи", "Бизнес, финансы, ресурсы", R.drawable.ic_sevens, 0.75f),
                Achievement("Охотник за богатствами", "Исследования и приключения", R.drawable.ic_chest, 0.75f),
                Achievement("Легенда коллекций", "Собирание предметов, коллекций", R.drawable.ic_book, 0.75f)
            )
        }

        return Profile(
            username = username,
            avatarResId = avatarResId,
            stats = stats,
            achievements = achievements
        )
    }

    fun updateProfile(username: String, avatarResId: Int, stats: Map<String, Int>? = null, achievements: List<Achievement>? = null): Profile {
        val currentProfile = getProfile()
        // Сохраняем новые данные в SharedPreferences
        with(sharedPreferences.edit()) {
            putString(KEY_USERNAME, username)
            putInt(KEY_AVATAR_RES_ID, avatarResId)
            if (stats != null) {
                val statsJson = gson.toJson(stats)
                putString(KEY_STATS, statsJson)
            }
            if (achievements != null) {
                val achievementsJson = gson.toJson(achievements)
                putString(KEY_ACHIEVEMENTS, achievementsJson)
            }
            apply()
        }
        return currentProfile.copy(
            username = username,
            avatarResId = avatarResId,
            stats = stats ?: currentProfile.stats,
            achievements = achievements ?: currentProfile.achievements
        )
    }
}