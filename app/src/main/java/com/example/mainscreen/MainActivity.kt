package com.example.mainscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mainscreen.ui.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("automats") { AutomatsScreen(navController) }
        composable("events") { EventsScreen(navController) }
        composable("collections") { CollectionsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}

@Composable
fun AutomatsScreenMS(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F7472))
    ) {
        Text(
            text = "Экран игровых автоматов",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        NavigationBar(navController)
    }
}

@Composable
fun EventsScreenMS(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F7472))
    ) {
        Text(
            text = "Экран событий",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        NavigationBar(navController)
    }
}

@Composable
fun CollectionsScreenMS(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F7472))
    ) {
        Text(
            text = "Экран коллекций",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        NavigationBar(navController)
    }
}

@Composable
fun ProfileScreenMS(navController: NavHostController) {
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
        NavigationBar(navController)
    }
}