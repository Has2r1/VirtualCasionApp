package com.example.mainscreen.data

import android.content.Context
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class MultiplierRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("multiplier_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LAST_LOGIN_DATE = "last_login_date"
        private const val KEY_MULTIPLIER = "multiplier"
        private const val KEY_STREAK = "streak"
    }

    // Получить текущий множитель
    fun getMultiplier(): Float {
        return sharedPreferences.getFloat(KEY_MULTIPLIER, 1.1f)
    }

    // Получить количество дней до сброса
    fun getDaysUntilReset(): Int {
        val today = LocalDate.now()

        val daysUntilNextMonday = (DayOfWeek.MONDAY.value - today.dayOfWeek.value + 7) % 7

        return if (daysUntilNextMonday == 0) 7 else daysUntilNextMonday
    }

    // Обновить множитель и счётчик входов
    fun updateMultiplier() {
        val today = LocalDate.now()
        val lastLoginDateStr = sharedPreferences.getString(KEY_LAST_LOGIN_DATE, null)
        val lastLoginDate = lastLoginDateStr?.let { LocalDate.parse(it) }

        var streak = sharedPreferences.getInt(KEY_STREAK, 0)
        var multiplier = sharedPreferences.getFloat(KEY_MULTIPLIER, 1.1f)

        if (today.dayOfWeek == DayOfWeek.MONDAY) {
            val lastMonday = lastLoginDate?.with(DayOfWeek.MONDAY)
            if (lastMonday == null || lastMonday < today) {
                streak = 0
                multiplier = 1.1f
            }
        }

        // Проверяем, был ли вход вчера
        if (lastLoginDate != null) {
            val daysBetween = ChronoUnit.DAYS.between(lastLoginDate, today).toInt()
            when (daysBetween) {
                0 -> {
                    return
                }
                1 -> {
                    streak++
                }
                else -> {
                    streak = 1
                }
            }
        } else {
            streak = 1
        }

        // Рассчитываем множитель
        multiplier = 1.1f + (streak - 1) * 0.1f
        if (multiplier > 1.7f) {
            multiplier = 1.7f
            streak = 7
        }

        // Сохраняем данные
        with(sharedPreferences.edit()) {
            putString(KEY_LAST_LOGIN_DATE, today.toString())
            putInt(KEY_STREAK, streak)
            putFloat(KEY_MULTIPLIER, multiplier)
            apply()
        }

        println("Multiplier updated: $multiplier, streak: $streak, last login: $today")
    }
}