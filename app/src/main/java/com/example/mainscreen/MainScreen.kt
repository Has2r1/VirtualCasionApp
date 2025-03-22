package com.example.mainscreen


import com.example.mainscreen.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mainscreen.data.EventsRepository
import com.example.mainscreen.data.MultiplierRepository
import com.example.mainscreen.presentation.MainScreenViewModel
import com.example.mainscreen.presentation.MainScreenViewModelFactory
import com.example.mainscreen.presentation.ProfileState
import com.example.mainscreen.presentation.ProfileViewModel
import com.example.mainscreen.presentation.ProfileViewModelFactory
import com.example.mainscreen.presentation.TimeViewModel
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    navController: NavHostController,
    multiplierRepository: MultiplierRepository,
    eventsRepository: EventsRepository,
    timeViewModel: TimeViewModel
    ) {

    val viewModel: MainScreenViewModel = viewModel(
        factory = MainScreenViewModelFactory(multiplierRepository, eventsRepository, timeViewModel)
    )
    val multiplier by viewModel.multiplier
    val daysUntilReset by viewModel.daysUntilReset
    val activeEvents by viewModel.activeEvents

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.updateMultiplier()
            viewModel.updateActiveEvents()
            delay(1_000L)
        }
    }

    val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(LocalContext.current))
    val profileState by profileViewModel.profileState

    val goldBalance = (profileState as? ProfileState.Success)?.goldBalance ?: 0
    val diamondBalance = (profileState as? ProfileState.Success)?.diamondBalance ?: 0

    fun getDaysText(days: Int): String {
        return when {
            days % 10 == 1 && days % 100 != 11 -> "$days день"
            days % 10 in 2..4 && days % 100 !in 12..14 -> "$days дня"
            else -> "$days дней"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.main_page_bckg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Row для обеих плашек с валютами (Gold и Diamond)
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                // Круг с иконкой
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

                // Текст с обводкой (наложение двух текстов)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = goldBalance.toString(),
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
                        text = goldBalance.toString(),
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
                // Круг с иконкой
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

                // Текст с обводкой (наложение двух текстов)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = diamondBalance.toString(),
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
                        text = diamondBalance.toString(),
                        fontSize = 22.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Плашка с множителем и днями до сброса
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 80.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF993232), Color(0xFF661111)),
                        start = Offset(0f, 0f),
                        end = Offset(0f, 200f)
                    )
                )
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "x ${String.format("%.1f", multiplier)}",
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    )
                )
                Text(
                    text = "${getDaysText(daysUntilReset)} до сброса",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    )
                )
            }
        }

        // Event Blocks (горизонтально в правом верхнем углу)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (activeEvents.isEmpty()) {
                // Плашка "Нет активных событий"
                Card(
                    modifier = Modifier
                        .width(350.dp)
                        .height(150.dp)
                        .padding(4.dp)
                        .border(1.dp, Color(0xFF11403F), RoundedCornerShape(20.dp))
                        .clickable { navController.navigate("events") },
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color(0xFF1F7472),
                    elevation = 4.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Нет активных событий",
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = androidx.compose.ui.graphics.Shadow(
                                    color = Color.Black,
                                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                                    blurRadius = 3f
                                )
                            )
                        )
                    }
                }
            } else {
                // Отображаем до 3 активных событий
                activeEvents.forEach { activeEvent ->
                    EventBlock(
                        title = activeEvent.event.title,
                        progress = activeEvent.progress,
                        time = activeEvent.timeRemaining,
                        onClick = { navController.navigate("events") }
                    )
                }
            }
        }

        // Column для кнопок Коллекции и Игровые Автоматы
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp, top = 80.dp)
        ) {
            // Collections Button
            CollectionsButton { navController.navigate("collections") }

            Spacer(modifier = Modifier.height(12.dp))

            // Game Automats Button
            GameAutomatsButton { navController.navigate("automats") }
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
fun EventBlock(
    title: String,
    progress: Int,
    time: String,
    onClick: () -> Unit = {} // Добавляем параметр для клика
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(4.dp)
            .clickable(onClick = onClick), // Добавляем кликабельность
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF1F7472),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    ),
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(20.dp)
                    .border((0.5).dp, Color(0xFF11403F), RoundedCornerShape(40.dp))
                    .clip(RoundedCornerShape(40.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFAF6060))
                        .clip(RoundedCornerShape(40.dp))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress / 100f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(0xFF993232))
                )

                Text(
                    text = "$progress%",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black.copy(alpha = 0.5f),
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }

            Text(
                text = time,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                        blurRadius = 2f
                    )
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun CollectionsButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Color(0xFF11403F), RoundedCornerShape(30.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2DA6A3), Color(0xFF1F7472)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 150f)
                )
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = "Коллекции",
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black,
                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                    blurRadius = 2f
                )
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Composable
fun GameAutomatsButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Color(0xFF993232), RoundedCornerShape(30.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF993232), Color(0xFF331111)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 150f)
                )
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = "Игровые Автоматы",
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black,
                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                    blurRadius = 2f
                )
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Composable
fun NavigationBar(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(90.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, Color.Black.copy(alpha = 0.8f), RoundedCornerShape(30.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.nav_bar_bckg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Black.copy(alpha = 0.5f))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavButton("Главная") { navController.navigate("main") }
            NavButton("Автоматы") { navController.navigate("automats") }
            NavButton("События") { navController.navigate("events") }
            NavButton("Коллекции") { navController.navigate("collections") }
            NavButton("Профиль") { navController.navigate("profile") }
        }
    }
}

@Composable
fun NavButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .fillMaxHeight()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Color(0xFF11403F), RoundedCornerShape(30.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2DA6A3), Color(0xFF1F7472)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                    blurRadius = 2f
                )
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp)
        )
    }
}