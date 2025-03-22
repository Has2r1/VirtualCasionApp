package com.example.mainscreen.ui

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mainscreen.R
import com.example.mainscreen.AchievmentItem
import com.example.mainscreen.NavigationBar
import com.example.mainscreen.data.Achievement
import com.example.mainscreen.presentation.ProfileState
import com.example.mainscreen.presentation.ProfileViewModel
import com.example.mainscreen.presentation.ProfileViewModelFactory

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(context))
    val profileState by viewModel.profileState

    val showEditDialog = remember { mutableStateOf(false) }

    var newUsername by remember {
        mutableStateOf(
            when (profileState) {
                is ProfileState.Success -> (profileState as ProfileState.Success).profile.username
                else -> "Username"
            }
        )
    }
    var newAvatarResId by remember {
        mutableStateOf(
            when (profileState) {
                is ProfileState.Success -> (profileState as ProfileState.Success).profile.avatarResId
                else -> R.drawable.ic_avatar
            }
        )
    }

    // Лаунчер для выбора изображения
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                println("Выбрано изображение: $uri")
                newAvatarResId = R.drawable.ic_avatar // Замени на реальную логику
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.profile_screen_bckg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Row для кнопки "Назад", аватара, имени пользователя, кнопки редактирования и демонстрационных кнопок
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Назад"
            Box(
                modifier = Modifier
                    .width(125.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable(onClick = { navController.popBackStack() })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_btn_bckg),
                    contentDescription = "Back Button Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.8f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Назад",
                    fontSize = 22.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Круглый бокс для аватара пользователя
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF2DA6A3), CircleShape)
                    .background(Color(0xFF77C5C4))
            ) {
                Image(
                    painter = painterResource(
                        id = when (profileState) {
                            is ProfileState.Success -> (profileState as ProfileState.Success).profile.avatarResId
                            else -> R.drawable.ic_avatar
                        }),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Закруглённый бокс для имени пользователя
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .defaultMinSize(minWidth = 150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (profileState) {
                        is ProfileState.Success -> (profileState as ProfileState.Success).profile.username
                        else -> "Username"
                    },
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопка редактирования
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .clickable(onClick = { showEditDialog.value = true })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit Button",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(40.dp))

            // Кнопка для увеличения прогресса первого достижения
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .background(Color(0xFF993232))
                    .clickable(onClick = {
                        if (profileState is ProfileState.Success) {
                            val currentProfile = (profileState as ProfileState.Success).profile
                            val updatedAchievements = currentProfile.achievements.toMutableList()
                            val firstAchievement = updatedAchievements[0]
                            val newProgress = (firstAchievement.progress + 0.1f).coerceAtMost(1.0f)
                            updatedAchievements[0] = firstAchievement.copy(progress = newProgress)
                            viewModel.updateAchievements(updatedAchievements)
                        }
                    })
            ) {
                Text(
                    text = "Увеличить прогресс",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопка для уменьшения прогресса первого достижения
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .background(Color(0xFF993232))
                    .clickable(onClick = {
                        if (profileState is ProfileState.Success) {
                            val currentProfile = (profileState as ProfileState.Success).profile
                            val updatedAchievements = currentProfile.achievements.toMutableList()
                            val firstAchievement = updatedAchievements[0]
                            val newProgress = (firstAchievement.progress - 0.1f).coerceAtLeast(0.0f)
                            updatedAchievements[0] = firstAchievement.copy(progress = newProgress)
                            viewModel.updateAchievements(updatedAchievements)
                        }
                    })
            ) {
                Text(
                    text = "Уменьшить прогресс",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопка для увеличения количества спинов
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .background(Color(0xFF993232))
                    .clickable(onClick = {
                        if (profileState is ProfileState.Success) {
                            val currentProfile = (profileState as ProfileState.Success).profile
                            val updatedStats = currentProfile.stats.toMutableMap()
                            val currentSpins = updatedStats["Количество спинов:"] ?: 0
                            updatedStats["Количество спинов:"] = currentSpins + 1
                            viewModel.updateStats(updatedStats)
                        }
                    })
            ) {
                Text(
                    text = "Добавить спин",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    )
                )
            }
        }

        // Строка с двумя столбцами в центре
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .height(300.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Первый столбец: блок со статистикой пользователя
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFF2DA6A3))
                    .border(2.dp, Color(0xFF11403F), RoundedCornerShape(15.dp))
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Обёрнутый заголовок "СТАТИСТИКА:"
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF77C5C4))
                            .border(0.5.dp, Color(0xFF1E6C6B), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "СТАТИСТИКА:",
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 3f
                                )
                            )
                        )
                    }

                    // Элементы статистики в виде строк
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 7.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        when (profileState) {
                            is ProfileState.Loading -> {
                                Text(
                                    text = "Загрузка...",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                            is ProfileState.Error -> {
                                Text(
                                    text = (profileState as ProfileState.Error).message,
                                    fontSize = 16.sp,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center
                                )
                            }
                            is ProfileState.Success -> {
                                val stats = (profileState as ProfileState.Success).profile.stats
                                stats.forEach { (label, value) ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = label,
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Start,
                                            style = androidx.compose.ui.text.TextStyle(
                                                shadow = Shadow(
                                                    color = Color.Black,
                                                    offset = Offset(2f, 2f),
                                                    blurRadius = 3f
                                                )
                                            )
                                        )
                                        Text(
                                            text = value.toString(),
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.End,
                                            style = androidx.compose.ui.text.TextStyle(
                                                shadow = Shadow(
                                                    color = Color.Black,
                                                    offset = Offset(2f, 2f),
                                                    blurRadius = 2f
                                                )
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Второй столбец: две строки с блоками достижений
            Column(
                modifier = Modifier
                    .width(450.dp)
                    .height(400.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Первая строка с двумя блоками достижений
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    when (profileState) {
                        is ProfileState.Success -> {
                            val achievements = (profileState as ProfileState.Success).profile.achievements
                            AchievmentItem(
                                title = achievements[0].title,
                                description = achievements[0].description,
                                iconResId = achievements[0].iconResId,
                                progress = achievements[0].progress
                            )
                            AchievmentItem(
                                title = achievements[1].title,
                                description = achievements[1].description,
                                iconResId = achievements[1].iconResId,
                                progress = achievements[1].progress
                            )
                        }
                        else -> {
                            Text("Загрузка достижений...")
                        }
                    }
                }

                // Вторая строка с двумя блоками достижений
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    when (profileState) {
                        is ProfileState.Success -> {
                            val achievements = (profileState as ProfileState.Success).profile.achievements
                            AchievmentItem(
                                title = achievements[2].title,
                                description = achievements[2].description,
                                iconResId = achievements[2].iconResId,
                                progress = achievements[2].progress
                            )
                            AchievmentItem(
                                title = achievements[3].title,
                                description = achievements[3].description,
                                iconResId = achievements[3].iconResId,
                                progress = achievements[3].progress
                            )
                        }
                        else -> {
                            Text("Загрузка достижений...")
                        }
                    }
                }
            }
        }

        // Всплывающее окно для редактирования профиля
        if (showEditDialog.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        onClick = { showEditDialog.value = false },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .zIndex(1f)
            ) {
                // Модальное окно
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(300.dp)
                        .height(240.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF2DA6A3))
                        .border(2.dp, Color(0xFF11403F), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                        .clickable(
                            onClick = { /* Пустой обработчик */ },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                        .zIndex(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Круг с аватаром
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFF2DA6A3), CircleShape)
                                .background(Color(0xFF77C5C4))
                                .clickable(onClick = { launcher.launch("image/*") })
                        ) {
                            Image(
                                painter = painterResource(id = newAvatarResId),
                                contentDescription = "User Avatar",
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.Center)
                                    .border((0.5).dp, Color.Black, CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Поле ввода для имени пользователя
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, Color(0xFF11403F), RoundedCornerShape(10.dp))
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xFF11403F), Color(0xFF2DA6A3)),
                                        start = Offset(0f, 150f),
                                        end = Offset(0f, 0f)
                                    )
                                )
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicTextField(
                                value = newUsername,
                                onValueChange = { newUsername = it },
                                textStyle = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 2.sp,
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2f, 2f),
                                        blurRadius = 3f
                                    )
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Кнопка сохранения
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border((0.5).dp, Color.Black, RoundedCornerShape(10.dp))
                                .background(Color(0xFF77C5C4))
                                .clickable(onClick = {
                                    if (newUsername.isNotEmpty()) {
                                        viewModel.updateProfile(newUsername, newAvatarResId)
                                        showEditDialog.value = false
                                    }
                                })
                        ) {
                            Text(
                                text = "Сохранить",
                                fontSize = 18.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center),
                                style = androidx.compose.ui.text.TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(2f, 2f),
                                            blurRadius = 3f
                                        )
                                )
                            )
                        }
                    }
                }
            }
        }

        // Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            NavigationBar(navController)
        }
    }
}