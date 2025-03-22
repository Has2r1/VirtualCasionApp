package com.example.mainscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mainscreen.data.Event
import com.example.mainscreen.presentation.ProfileState
import com.example.mainscreen.presentation.ProfileViewModel
import com.example.mainscreen.presentation.ProfileViewModelFactory
import com.example.mainscreen.presentation.TimeState
import com.example.mainscreen.presentation.TimeViewModel
import kotlinx.coroutines.delay

@Composable
fun EventDetailedBlock(
    event: Event,
    timeViewModel: TimeViewModel,
    onEventUpdate: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(LocalContext.current))
    val profileState by profileViewModel.profileState

    val timeState by timeViewModel.timeState

    var currentTime by remember { mutableStateOf(System.currentTimeMillis() / 1000) }

    LaunchedEffect(Unit) {
        while (true) {
            println("Fetching current time...")
            timeViewModel.fetchCurrentTime("UTC")
            delay(1000L)
        }
    }

    LaunchedEffect(timeState) {
        println("TimeState updated: $timeState")
        when (timeState) {
            is TimeState.Success -> {
                currentTime = (timeState as TimeState.Success).timeResponse.unixtime
                println("Current time updated: $currentTime")
            }
            is TimeState.Error -> {
                println("Time fetch error: ${(timeState as TimeState.Error).message}")
            }
            is TimeState.Loading -> {
                println("Time is loading...")
            }
        }
    }

    val startTime = event.startTime
    val endTime = event.endTime

    val isStarted = startTime != null
    val isCompleted = endTime != null && startTime != null && currentTime >= endTime
    // println("isStarted: $isStarted, isCompleted: $isCompleted, endTime: $endTime, currentTime: $currentTime")

    val progress = if (isStarted && !isCompleted && endTime != null && startTime != null) {
        val elapsedTime = currentTime - startTime
        val totalDuration = endTime - startTime
        // println("Calculating progress: elapsedTime=$elapsedTime, totalDuration=$totalDuration, currentTime=$currentTime, startTime=$startTime, endTime=$endTime")
        if (totalDuration > 0) {
            ((elapsedTime.toFloat() / totalDuration) * 100).coerceIn(0f, 100f).toInt()
        } else 0
    } else if (isCompleted) {
        100
    } else {
        0
    }

    val timeRemaining = if (currentTime == 0L) {
        "Загрузка..."
    } else if (isStarted && !isCompleted && endTime != null) {
        val remainingSeconds = (endTime - currentTime).coerceAtLeast(0)
        // println("Calculating timeRemaining: remainingSeconds=$remainingSeconds, endTime=$endTime, currentTime=$currentTime")
        val hours = remainingSeconds / 3600
        val minutes = (remainingSeconds % 3600) / 60
        val seconds = remainingSeconds % 60
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else if (isCompleted) {
        "Завершено"
    } else {
        val durationSeconds = event.duration
        val hours = durationSeconds / 3600
        val minutes = (durationSeconds % 3600) / 60
        val seconds = durationSeconds % 60
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    val (buttonText, buttonAction) = when {
        !isStarted -> "Начать" to {
            if (currentTime > 0) {
                val updatedEvent = event.copy(startTime = currentTime)
                onEventUpdate(updatedEvent)
            } else {
                println("Cannot start event: currentTime is not yet available")
            }
        }
        isStarted && !isCompleted -> "Отменить" to {
            val updatedEvent = event.copy(startTime = null)
            onEventUpdate(updatedEvent)
        }
        isCompleted -> "Получить" to {
            if (profileState is ProfileState.Success) {
                val rewardValue = event.reward.split(" ")[0].toIntOrNull() ?: 0
                val currentGold = (profileState as ProfileState.Success).goldBalance
                profileViewModel.updateGoldBalance(currentGold + rewardValue)

                val updatedEvent = event.copy(startTime = null)
                onEventUpdate(updatedEvent)
            }
        }
        else -> "Отменить" to {}
    }

    Card(
        modifier = modifier
            .width(150.dp)
            .height(220.dp)
            .padding(8.dp)
            .border(2.dp, Color(0xFF11403F), RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF2DA6A3),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = event.title,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(46.dp)
                    .border(1.dp, Color(0xFF11403F), RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF77C5C4))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ожидаемая награда: \n${event.reward}",
                    fontSize = 11.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.7f),
                            offset = Offset(2f, 2f),
                            blurRadius = 1f
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Показываем ошибку, если не удалось загрузить время
            if (timeState is TimeState.Error) {
                Text(
                    text = "Ошибка: ${(timeState as TimeState.Error).message}",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(20.dp)
                    .border((0.5).dp, Color(0xFFA25757), RoundedCornerShape(40.dp))
                    .clip(RoundedCornerShape(40.dp)),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFD4A59A))
                        .clip(RoundedCornerShape(40.dp))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress / 100f)
                        .height(25.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(0xFFC86B6B))
                )

                Text(
                    text = "$progress%",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.7f),
                            offset = Offset(2f, 2f),
                            blurRadius = 1f
                        )
                    ),
                    modifier = Modifier
                        .matchParentSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            Text(
                text = timeRemaining,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.7f),
                        offset = Offset(2f, 2f),
                        blurRadius = 3f
                    )
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(top = 4.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(30.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFEE7F7F), Color(0xFFB22222))
                        )
                    )
                    .clickable(onClick = buttonAction),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonText,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.7f),
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    )
                )
            }
        }
    }
}