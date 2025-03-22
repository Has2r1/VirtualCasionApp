package com.example.mainscreen.data

import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Profile(
    val username: String,
    val avatarResId: Int,
    val stats: Map<String, Int>,
    val achievements: List<Achievement>,
    val goldBalance: Int = 0,
    val diamondBalance: Int = 0
)

data class Achievement(
    val title: String,
    val description: String,
    val iconResId: Int,
    val progress: Float
)

data class Event(
    val title: String,
    val reward: String,
    val duration: Long,
    var startTime: Long? = null

) {
    val endTime: Long?
        get() = startTime?.let { it + duration }
}

data class TimeResponse(
    @SerializedName("datetime") val currentLocalTime: String?,
    @SerializedName("timezone") val timeZone: String?
) {
    val unixtime: Long
        get() {
            if (currentLocalTime == null) {
                println("Error: currentLocalTime is null")
                return 0L
            }
            return try {
                val cleanedTime = if (currentLocalTime.contains("Z[")) {
                    currentLocalTime.substring(0, currentLocalTime.indexOf("Z[")) + "Z"
                } else {
                    currentLocalTime + "Z"
                }
                val formatter = DateTimeFormatter.ISO_DATE_TIME
                val zonedDateTime = ZonedDateTime.parse(cleanedTime, formatter)
                zonedDateTime.toEpochSecond()
            } catch (e: Exception) {
                println("Error parsing currentLocalTime: $e")
                0L
            }
        }
}