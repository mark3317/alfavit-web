package ru.markn.alfavitsad.pres.main

import alfavit_web.app.generated.resources.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitsad.domain.models.Person
import ru.markn.alfavitsad.pres.utils.components.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IMainActions.MainScreen(state: MainUIState) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val windowWidth = maxWidth
        val windowHeight = maxHeight
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .matchParentSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AppBlockHome(windowHeight)
            AppBlockAbout()
            AppBlockActivities(windowWidth)
            SharedTransitionLayout {
                val persons = listOf(
                    Person(
                        name = "Фоменко Анна",
                        photo = Res.drawable.pers3,
                        details = "Заведующая детского сада Алфавит. Имеет высшее экономическое образование по специальности «Финансы и Кредит». Окончила КГСХА. Имеет большой опыт работы в образовательной сфере, в частности в школе Английского языка. Рада помочь вам в любых вопросах связанных с нашим садом."
                    ),
                    Person(
                        name = "Кудрявцева Юлия",
                        photo = Res.drawable.pers1,
                        details = "Преподаватель начальных классов. Проводит занятия «Подготовка к школе» как в группе, так и индивидуально. А также ведёт репетиторство по основным предметам начальной школы. Образование: 2 высших - ТМИ и ЮУрГУ. Имеет научную публикацию по теме детско-родительских отношений."
                    ),
                    Person(
                        name = "Иванова Надежда",
                        photo = Res.drawable.pers2,
                        details = "Воспитатель старшей группы. Имеет высшее педагогическое образование, богатейший опыт работы с детьми начальных классов. Стаж работы с детьми более 28 лет. Специалист широкого профиля."
                    ),
                    Person(
                        name = "Васютинская Анна",
                        photo = Res.drawable.pers4,
                        details = "Воспитатель младшей группы. Инструктор по физической культуре. Имеет высшее образование и 20-ти летний стаж работы в образовательной сфере. В работе с детьми использует методы соответствующие их возрастным и индивидуальным особенностям. Для повышения эффективности образования использует нестандартные формы занятий: занятия по физическому воспитанию. В различных видах деятельный развивает в своих воспитанниках творческие и интеллектуальные способности, умение логически мыслить, проявлять инициативу и самостоятельность."
                    ),
                )
                var selectedPerson by remember { mutableStateOf(persons.first()) }

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(color = Color(0xFF619B8A)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 52.dp),
                        text = "Наш коллектив",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 46.sp,
                            fontFamily = AppTheme.FontFamily,
                            color = Color.White,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.7f)
                            .widthIn(min = 800.dp, max = 1200.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                        ) {
                            persons.forEach { person ->
                                AnimatedVisibility(
                                    visible = selectedPerson == person,
                                ) {
                                    PersonDetailsCard(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxSize(),
                                        sharedTransitionScope = this@SharedTransitionLayout,
                                        animationVisibilityScope = this@AnimatedVisibility,
                                        person = person,
                                    )
                                }
                            }
                        }
                        LazyRow(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxHeight(0.3f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(items = persons) { person ->
                                AnimatedVisibility(
                                    modifier = Modifier.animateItem(),
                                    visible = selectedPerson != person,
                                ) {
                                    PersonCard(
                                        sharedTransitionScope = this@SharedTransitionLayout,
                                        animationVisibilityScope = this@AnimatedVisibility,
                                        person = person,
                                        onClick = { selectedPerson = person }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            BasicText(
                text = "${state.title} windowWidth: $windowWidth, windowHeight: $windowHeight",
                autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 20.sp),
            )
        }
        AppHeader(modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun AppBlockHome(windowHeight: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(windowHeight)
                .drawWithContent {
                    drawContent()
                    drawRect(Color.Black.copy(alpha = 0.5f))
                },
            painter = painterResource(Res.drawable.home),
            contentDescription = "Img Example",
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(74.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicText(
                modifier = Modifier.width(240.dp),
                text = "Частный детский сад в Тюмени",
                autoSize = TextAutoSize.StepBased(minFontSize = 8.sp, maxFontSize = 18.sp),
                maxLines = 2,
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )
            BasicText(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = "Детский сад «Алфавит»",
                maxLines = 2,
                autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 90.sp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppTheme.FontFamily,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
            BasicText(
                modifier = Modifier.width(284.dp),
                text = "Наши двери открыты для детей от 1 года до 7 лет",
                maxLines = 2,
                autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 18.sp),
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun AppBlockAbout() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color(0xFFFE7F2D)),
        contentAlignment = Alignment.Center,
    ) {
        FlowRow(
            modifier = Modifier
                .padding(vertical = 52.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            itemVerticalAlignment = Alignment.CenterVertically,
        ) {
            BasicText(
                modifier = Modifier.widthIn(max = 800.dp),
                text = "С любовью, заботой и знаниями мы помогаем каждому ребенку расти счастливым, здоровым, любознательным и готовым к открытиям в мире знаний!",
                autoSize = TextAutoSize.StepBased(minFontSize = 18.sp, maxFontSize = 34.sp),
                maxLines = 5,
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = AppTheme.FontFamily,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 2f
                    )
                )
            )
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(Res.drawable.kubiki),
                contentDescription = "Kubiki Image",
            )
        }
    }
}

@Composable
fun AppBlockActivities(windowWidth: Dp) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 52.dp),
            text = "Наши занятия",
            style = TextStyle(
                fontSize = 46.sp,
                fontFamily = AppTheme.FontFamily,
            )
        )
        Column(
            modifier = Modifier.padding(vertical = 36.dp)
        ) {
            if (windowWidth < 920.dp) {
                val pagerState = rememberPagerState(pageCount = { 6 })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(520.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(horizontal = windowWidth / 2 - 150.dp),
                    pageSpacing = 24.dp,
                    pageSize = PageSize.Fixed(300.dp)
                ) { page ->
                    val percentPageOffset =
                        1f - ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue.coerceIn(0f, 1f)
                    ActivityCard(
                        modifier = Modifier
                            .size(width = 300.dp, height = (450 + (50 * percentPageOffset)).dp)
                            .graphicsLayer {
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = percentPageOffset
                                )
                            }
                    )
                }
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
            } else {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(36.dp),
                    itemVerticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(times = 6) {
                        ActivityCard(
                            modifier = Modifier.size(width = 300.dp, height = 500.dp)
                        )
                    }
                }
            }
        }
    }
}
