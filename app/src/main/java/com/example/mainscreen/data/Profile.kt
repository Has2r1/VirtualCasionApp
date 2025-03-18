package com.example.mainscreen.data

data class Profile(
    val username: String,
    val avatarResId: Int,
    val stats: Map<String, Int>,
    val achievements: List<Achievement>
)

data class Achievement(
    val title: String,
    val description: String,
    val iconResId: Int,
    val progress: Float
)