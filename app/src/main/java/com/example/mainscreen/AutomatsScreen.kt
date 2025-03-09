package com.example.mainscreen

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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shadow
import androidx.navigation.NavHostController

@Composable
fun AutomatsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.automats_screen_bckg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Column для плашки с валютой и кнопки "Назад"
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Gold Balance (Плашка с валютой)
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
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
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

            Spacer(modifier = Modifier.height(12.dp))

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
        }

        // Кнопка в правом верхнем углу
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .width(125.dp)
                .height(40.dp)
                .clickable(onClick = { /* Действие для кнопки */ })
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_btn),
                contentDescription = "Right Top Button Background",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )
        }

        // Большой блок посередине
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(600.dp)
                .height(250.dp)
                .padding(top = 20.dp) // Увеличил отступ сверху, чтобы подвинуть блок выше
                .clip(RoundedCornerShape(20.dp)) // Закругление краёв
                .background(Color.Black.copy(alpha = 0.8f)) // Потемнённый фон
        ) {
            Image(
                painter = painterResource(id = R.drawable.slot_machine),
                contentDescription = "Slot Machine",
                modifier = Modifier
                    .fillMaxSize() // Заполняет весь Box
                    .clip(RoundedCornerShape(20.dp)) // Закругление краёв изображения
                    .alpha(0.9f),
                contentScale = ContentScale.Crop // Изменён на Crop, чтобы убрать боковые отступы
            )

            // Три блока посередине
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(180.dp) // Высота блоков, чтобы они занимали основную часть
                    .padding(horizontal = 20.dp), // Отступы от краёв
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Первый блок
                Box(
                    modifier = Modifier
                        .width(120.dp) // Ширина каждого блока
                        .height(180.dp) // Высота блока
                        .clip(RoundedCornerShape(10.dp)) // Лёгкое закругление
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_slot), // Замени на иконку (например, с буквой "F")
                        contentDescription = "Slot Icon 1",
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(Modifier.width((0.2).dp))
                // Второй блок
                Box(
                    modifier = Modifier
                        .width(120.dp) // Ширина каждого блока
                        .height(180.dp) // Высота блока
                        .clip(RoundedCornerShape(10.dp)) // Лёгкое закругление
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_slot), // Замени на иконку (например, с буквой "F")
                        contentDescription = "Slot Icon 2",
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(Modifier.width((0.2).dp))
                // Третий блок
                Box(
                    modifier = Modifier
                        .width(120.dp) // Ширина каждого блока
                        .height(180.dp) // Высота блока
                        .clip(RoundedCornerShape(10.dp)) // Лёгкое закругление
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_slot), // Замени на иконку (например, с буквой "F")
                        contentDescription = "Slot Icon 3",
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        // Строка между навигационной панелью и блоком посередине
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp) // Увеличил отступ от навигационной панели
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp), // Отступы от краёв
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Сместил ближе к центру
        ) {
            // Левый бокс (Ставка)
            Box(
                modifier = Modifier
                    .width(280.dp) // Увеличил в 2 раза
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .padding(horizontal = 16.dp), // Увеличил внутренний отступ
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ставка:",
                        fontSize = 22.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 2f
                            )
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 20.dp)
                    )

                    // Поле ввода для цифр с красным градиентом
                    var betValue by remember { mutableStateOf("1000") }
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(35.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF993232), Color(0xFF331111)),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, 150f)
                                )
                            )
                            .padding(horizontal = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            value = betValue,
                            onValueChange = { betValue = it },
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 2f
                                )
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp)) // Отступ между плашками

            // Центральная кнопка (Спин)
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp)
                    .clickable(onClick = { /* Действие для кнопки Спин */ })
                    //.background(Color.Black.copy(alpha = 0.8f))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spin_btn),
                    contentDescription = "Spin Button Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "СПИН",
                    fontSize = 28.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Отступ между плашками

            // Правый бокс (Выигрыш)
            Box(
                modifier = Modifier
                    .width(280.dp) // Оставляем ширину
                    .height(50.dp) // Оставляем высоту
                    .clip(RoundedCornerShape(20.dp)) // Закругление краёв
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2DA6A3), Color(0xFF11403F))
                        )
                    )
                    .padding(horizontal = 16.dp), // Увеличенный внутренний отступ
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Выигрыш:",
                        fontSize = 22.sp, // Увеличил шрифт до 22.sp, как в левом боксе
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 2f // Уменьшил blurRadius до 2f, как в левом боксе
                            )
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp) // Добавил padding, как в левом боксе
                    )

                    // Бокс с цифрами с красным градиентом
                    Box(
                        modifier = Modifier
                            .width(120.dp) // Увеличил ширину до 120.dp, как в левом боксе
                            .height(35.dp) // Увеличил высоту до 35.dp, как в левом боксе
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF993232), Color(0xFF331111)), // Использовал тот же градиент
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, 150f)
                                )
                            )
                            .padding(horizontal = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "9999",
                            fontSize = 20.sp, // Увеличил шрифт до 20.sp, как в левом боксе
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 2f // Добавил тень, как в левом боксе
                                )
                            )
                        )
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