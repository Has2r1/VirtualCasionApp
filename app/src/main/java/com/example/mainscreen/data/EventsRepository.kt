package com.example.mainscreen.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventsRepository(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("events_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getEvents(): List<Event> {
        val eventsJson = sharedPreferences.getString("events", null)
        return if (eventsJson != null) {
            gson.fromJson(eventsJson, object : TypeToken<List<Event>>() {}.type)
        } else {
            listOf(
                Event(
                    title = "Добыча рудника",
                    reward = "150 Золота",
                    duration = 60 // 1 час (заменено на 1 минуту для демонстрации)
                ),
                Event(
                    title = "Завод",
                    reward = "200 Золота",
                    duration = 7200 // 2 часа
                ),
                Event(
                    title = "Лесопилка",
                    reward = "120 Золота",
                    duration = 5400 // 1.5 часа
                ),
                Event(
                    title = "Ферма",
                    reward = "100 Золота",
                    duration = 1800 // 30 минут
                ),
                Event(
                    title = "Кузница",
                    reward = "250 Золота",
                    duration = 10800 // 3 часа
                ),
                Event(
                    title = "Экспедиция",
                    reward = "300 Золота",
                    duration = 14400 // 4 часа
                ),
                Event(
                    title = "Биржа",
                    reward = "500 Золота",
                    duration = 7200 // 2 часа
                ),
                Event(
                    title = "Лаборатория",
                    reward = "670 Золота",
                    duration = 10800 // 2.5 часа
                )
            )
        }
    }

    fun saveEvents(events: List<Event>) {
        val eventsJson = gson.toJson(events)
        sharedPreferences.edit().putString("events", eventsJson).apply()
    }

    fun updateEvents(events: List<Event>) {
        sharedPreferences.edit().apply {
            putString("events", gson.toJson(events))
            apply()
        }
    }
}