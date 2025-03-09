package com.example.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F7472))
    ) {
        Text(
            text = "Экран профиля",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        // Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            NavigationBar(navController)
        }
    }
}