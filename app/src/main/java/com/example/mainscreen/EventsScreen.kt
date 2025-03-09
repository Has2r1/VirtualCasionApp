package com.example.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EventsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.events_screen_bckg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Row для кнопки "Назад" и плашек с валютами (Gold и Diamond)
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
                    .clickable(onClick = { navController.popBackStack() }) // Возврат на предыдущий экран
            ) {
                // Фоновое изображение кнопки
                Image(
                    painter = painterResource(id = R.drawable.back_btn_bckg), // Укажи своё изображение
                    contentDescription = "Back Button Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                // Затемняющий слой (опционально, для лучшей читаемости текста)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.3f)) // Полупрозрачный чёрный слой
                )

                // Текст "Назад"
                Text(
                    text = "Назад",
                    fontSize = 22.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 3f
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Отступ между кнопкой и плашками

            // Gold Balance
            Row(
                modifier = Modifier
                    .width(125.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(1.dp, Color(0xFF2DA6A3), CircleShape)
                        .clip(CircleShape)
                        .background(Color(0xFF77C5C4))
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_gold),
                        contentDescription = "Gold Icon",
                        modifier = Modifier
                            .size(35.dp)
                            .border(1.dp, Color(0xFFBEE3E2), CircleShape)
                            .clip(CircleShape)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "4000",
                        fontSize = 22.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = androidx.compose.ui.graphics.Shadow(
                                color = Color.Black,
                                offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                                blurRadius = 3f
                            )
                        )
                    )

                    Text(
                        text = "4000",
                        fontSize = 22.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Diamond Balance
            Row(
                modifier = Modifier
                    .width(125.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(1.dp, Color(0xFF2DA6A3), CircleShape)
                        .clip(CircleShape)
                        .background(Color(0xFF77C5C4))
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diamond),
                        contentDescription = "Diamond Icon",
                        modifier = Modifier
                            .size(35.dp)
                            .border(1.dp, Color(0xFFBEE3E2), CircleShape)
                            .clip(CircleShape)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "4000",
                        fontSize = 22.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = androidx.compose.ui.graphics.Shadow(
                                color = Color.Black,
                                offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                                blurRadius = 3f
                            )
                        )
                    )

                    Text(
                        text = "4000",
                        fontSize = 22.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Row для 5 блоков посередине экрана
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 120.dp, bottom = 120.dp) // Отступы сверху и снизу, чтобы не перекрывать верхние и нижние элементы
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Заглушки для 5 блоков (временные, для проверки расположения)
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .background(Color(0xFF1F7472))
            ) { Text("Блок 1") }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .background(Color(0xFF1F7472))
            ) { Text("Блок 2") }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .background(Color(0xFF1F7472))
            ) { Text("Блок 3") }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .background(Color(0xFF1F7472))
            ) { Text("Блок 4") }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .background(Color(0xFF1F7472))
            ) { Text("Блок 5") }
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