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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.navigation.NavHostController

@Composable
fun CollectionsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.collections_screen_bckg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Row для кнопки "Назад" и плашеки с валютой (Gold)
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

            Spacer(modifier = Modifier.width(16.dp))

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
        }

        // Блоки коллекций (2 строки по 3 блока)
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 25.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CollectionItem(
                    title = "Древние артефакты",
                    description = "Собрание таинственных реликвий прошлых цивилизаций.",
                    iconResId = R.drawable.ic_artifact,
                    progress = 0.75f
                )
                CollectionItem(
                    title = "Легендарные самоцветы",
                    description = "Редкие драгоценные камни с магическими свойствами.",
                    iconResId = R.drawable.ic_gem,
                    progress = 0.5f
                )
                CollectionItem(
                    title = "Чудеса природы",
                    description = "Редкие минералы, уникальные растения и необычные природные явления.",
                    iconResId = R.drawable.ic_nature,
                    progress = 0.9f
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CollectionItem(
                    title = "Арсенал чемпиона",
                    description = "Коллекция редкого оружия и доспехов великих воинов.",
                    iconResId = R.drawable.ic_trophy,
                    progress = 0.3f
                )
                CollectionItem(
                    title = "Наследие богов",
                    description = "Символы силы древних божеств и могущественные реликвии.",
                    iconResId = R.drawable.ic_scroll,
                    progress = 0.6f
                )
                CollectionItem(
                    title = "Золотая лихорадка",
                    description = "Разнообразные самородки и золотые монеты со всего мира.",
                    iconResId = R.drawable.ic_gold_nugget,
                    progress = 0.8f
                )
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
fun CollectionItem(
    title: String,
    description: String,
    iconResId: Int,
    progress: Float = 0.75f, // Прогресс от 0f до 1f (75% по умолчанию)
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(250.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF2DA6A3))
            .border(2.dp, Color(0xFF11403F), RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Первый столбец: иконка по центру
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border((0.3).dp, Color.Black, CircleShape)
                    .background(Color(0xFF77C5C4)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "$title Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            // Второй столбец: название с прогресс-баром и описание
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Первая строка: название в боксе и прогресс-бар
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Бокс для названия
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF77C5C4))
                            .border(0.5.dp, Color(0xFF1E6C6B), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp),
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

                    Spacer(Modifier.width(7.dp))

                    // Круглый прогресс-бар
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(35.dp)
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
                        .fillMaxWidth(),
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