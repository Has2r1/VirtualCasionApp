package com.example.mainscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
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

        // Row для кнопки "Назад", аватара, имени пользователя и кнопки редактирования
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
                    .size(55.dp)
                    .clip(CircleShape)
                    .border((0.5).dp, Color.Black, CircleShape)
                    .background(Color(0xFF77C5C4))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar), // Замени на иконку аватара
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .border(2.dp, Color(0xFF2DA6A3), CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Закруглённый бокс для имени пользователя
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .border((0.5).dp, Color.Black, RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Username",
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
                    .size(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .border((0.5).dp, Color.Black, RoundedCornerShape(10.dp))
                    .clickable(onClick = { /* Логика редактирования будет прописана позже */ })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit), // Замени на иконку карандаша
                    contentDescription = "Edit Button",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
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
                        listOf(
                            "Собранные коллекции:" to "10",
                            "Полученные достижения:" to "10",
                            "Потраченная валюта:" to "10",
                            "Количество спинов:" to "10",
                            "Крупнейший выигрыш:" to "10",
                            "Завершенные события:" to "10",
                            "Количество дней входа:" to "10"
                        ).forEach { (label, value) ->
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
                                    text = value,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.End,
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
                    AchievmentItem(
                        title = "Покоритель удачи",
                        description = "Азартные и игровые достижения",
                        iconResId = R.drawable.ic_clover,
                        progress = 0.75f
                    )

                    AchievmentItem(
                        title = "Магнат удачи",
                        description = "Бизнес, финансы, ресурсы",
                        iconResId = R.drawable.ic_sevens,
                        progress = 0.75f
                    )
                }

                // Вторая строка с двумя блоками достижений
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AchievmentItem(
                        title = "Охотник за богатствами",
                        description = "Исследования и приключения",
                        iconResId = R.drawable.ic_chest,
                        progress = 0.75f
                    )

                    AchievmentItem(
                        title = "Легенда коллекций",
                        description = "Собирание предметов, коллекций",
                        iconResId = R.drawable.ic_book,
                        progress = 0.75f
                    )
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

@Composable
fun AchievmentItem(
    title: String,
    description: String,
    iconResId: Int,
    progress: Float = 0.75f,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF2DA6A3))
            .border(2.dp, Color(0xFF11403F), RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            // Первая строка: иконка, название и прогресс-бар
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Иконка
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border((0.3).dp, Color.Black, CircleShape)
                        .background(Color(0xFF77C5C4)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = "$title Icon",
                        modifier = Modifier.size(35.dp)
                    )
                }

                Spacer(modifier = Modifier.width(7.dp))

                // Название
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF77C5C4))
                        .border(0.5.dp, Color(0xFF1E6C6B), RoundedCornerShape(8.dp))
                        .padding(horizontal = 3.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 3f
                            )
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(7.dp))

                // Круглый прогресс-бар
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        // Фон круга
                        drawCircle(
                            color = Color.White,
                            radius = size.minDimension / 2,
                            style = Stroke(width = 6.dp.toPx())
                        )
                        // Прогресс
                        drawArc(
                            color = Color(color = 0xFF1E6C6B),
                            startAngle = -90f,
                            sweepAngle = 360f * progress,
                            useCenter = false,
                            style = Stroke(width = 6.dp.toPx()),
                            topLeft = Offset(0f, 0f),
                            size = size
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Вторая строка: описание
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
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