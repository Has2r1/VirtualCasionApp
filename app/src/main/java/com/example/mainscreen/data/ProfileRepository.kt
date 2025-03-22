package com.example.mainscreen.data

import android.content.Context
import android.content.SharedPreferences
import com.example.mainscreen.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileRepository(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getProfile(): Profile {
        val username = sharedPreferences.getString("username", "Username") ?: "Username"
        val avatarResId = sharedPreferences.getInt("avatar_res_id", R.drawable.ic_avatar)
        val statsJson = sharedPreferences.getString("stats", null)
        val achievementsJson = sharedPreferences.getString("achievements", null)
        val goldBalance = sharedPreferences.getInt("gold_balance", 0)
        val diamondBalance = sharedPreferences.getInt("diamond_balance", 0)

        val stats = if (statsJson != null) {
            gson.fromJson(statsJson, object : TypeToken<Map<String, Int>>() {}.type)
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

        val achievements = if (achievementsJson != null) {
            gson.fromJson(achievementsJson, object : TypeToken<List<Achievement>>() {}.type)
        } else {
            listOf(
                Achievement("Покоритель удачи", "Азартные и игровые достижения", R.drawable.ic_clover, 0.75f),
                Achievement("Магнат удачи", "Бизнес, финансы, ресурсы", R.drawable.ic_sevens, 0.75f),
                Achievement("Охотник за богатствами", "Исследования и приключения", R.drawable.ic_chest, 0.75f),
                Achievement("Легенда коллекций", "Собирание предметов, коллекций", R.drawable.ic_book, 0.75f)
            )
        }

        return Profile(username, avatarResId, stats, achievements, goldBalance, diamondBalance)
    }

    fun updateProfile(username: String, avatarResId: Int) {
        sharedPreferences.edit().apply {
            putString("username", username)
            putInt("avatar_res_id", avatarResId)
            apply()
        }
    }

    fun updateStats(stats: Map<String, Int>) {
        sharedPreferences.edit().apply {
            putString("stats", gson.toJson(stats))
            apply()
        }
    }

    fun updateAchievements(achievements: List<Achievement>) {
        sharedPreferences.edit().apply {
            putString("achievements", gson.toJson(achievements))
            apply()
        }
    }

    // Метод для обновления GoldBalance
    fun updateGoldBalance(goldBalance: Int) {
        sharedPreferences.edit().apply {
            putInt("gold_balance", goldBalance)
            apply()
        }
    }

    // Метод для обновления DiamondBalance
    fun updateDiamondBalance(diamondBalance: Int) {
        sharedPreferences.edit().apply {
            putInt("diamond_balance", diamondBalance)
            apply()
        }
    }
}