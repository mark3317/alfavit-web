package ru.markn.alfavitweb.pres.main

import alfavit_web.app.generated.resources.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import ru.markn.alfavitweb.domain.models.Activity
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.components.*
import ru.markn.alfavitweb.pres.utils.AppTheme
import kotlin.math.absoluteValue

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
            BlockHome(windowHeight)
            BlockAbout()
            BlockActivities(windowWidth)
            BlockTeam(windowWidth)

            val services = listOf(
                Service(
                    image = Res.drawable.service1,
                    title = "Группа полного дня",
                    color = Color(0xFFFE7F2D),
                    price = 19500
                ),
                Service(
                    image = Res.drawable.service2,
                    title = "Группа неполного дня",
                    color = Color(0xFFFCCA46),
                    price = 15000
                ),
                Service(
                    image = Res.drawable.service3,
                    title = "Единоразовое посещение",
                    color = Color(0xFF619B8A),
                    price = 1300
                ),
                Service(
                    image = Res.drawable.service4,
                    title = "Адаптация",
                    color = Color(0xFF37AE48),
                    price = 250
                ),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFF5F5F5))
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = "Наши услуги",
                    style = TextStyle(
                        fontSize = 46.sp,
                        fontFamily = AppTheme.FontFamily,
                    )
                )
                if (windowWidth < 920.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        services.forEach { service ->
                            ServiceCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                service = service,
                                onClick = {}
                            )
                        }
                    }
                } else {
                    FlowRow(
                        modifier = Modifier
                            .padding(horizontal = 64.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalArrangement = Arrangement.Center,
                        itemVerticalAlignment = Alignment.CenterVertically,
                    ) {
                        services.forEach { service ->
                            ServiceCard(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(width = 430.dp, height = 180.dp),
                                service = service,
                                onClick = {}
                            )
                        }
                    }
                }
            }
            BasicText(
                text = "${state.title} windowWidth: $windowWidth, windowHeight: $windowHeight",
                autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 20.sp),
            )
        }
        ScreenHeader(modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun ServiceCard(
    modifier: Modifier = Modifier,
    service: Service,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
            hoveredElevation = 16.dp,
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(service.image),
                contentDescription = "Service Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        width = 6.dp,
                        color = service.color,
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = service.title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
                Text(
                    text = "${service.price} ₽",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

@Composable
fun BlockHome(windowHeight: Dp) {
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
fun BlockAbout() {
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
fun BlockActivities(windowWidth: Dp) {
    val activities = listOf(
        Activity(
            image = Res.drawable.activity1,
            title = "Развивающие занятия",
            description = "Развитие речи, рисование, лепка, аппликация, математическое развитие, окружающий мир, музыка, физкультура"
        ),
        Activity(
            image = Res.drawable.activity2,
            title = "Подготовка к школе",
            description = "Подготовка к школе, развитие речи, математическое развитие, окружающий мир, музыка, физкультура"
        ),
        Activity(
            image = Res.drawable.activity3,
            title = "Развитие речи",
            description = "Развитие речи, логопедические занятия, подготовка к школе, математическое развитие, окружающий мир, музыка, физкультура"
        ),
        Activity(
            image = Res.drawable.activity4,
            title = "Развитие логики",
            description = "Развитие логики, математическое развитие, окружающий мир, музыка, физкультура"
        ),
        Activity(
            image = Res.drawable.activity5,
            title = "Развитие мелкой моторики",
            description = "Развитие мелкой моторики, математическое развитие, окружающий мир, музыка, физкультура"
        ),
        Activity(
            image = Res.drawable.activity6,
            title = "Развитие музыкального слуха",
            description = "Развитие музыкального слуха, математическое развитие, окружающий мир, музыка, физкультура"
        ),
    )
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color(0xFFF5F5F5))
            .padding(vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 28.dp),
            text = "Наши занятия",
            style = TextStyle(
                fontSize = 46.sp,
                fontFamily = AppTheme.FontFamily,
            )
        )
        if (windowWidth < 920.dp) {
            val pagerState = rememberPagerState(pageCount = activities::size)
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
                        .size(width = 300.dp, height = (420 + (50 * percentPageOffset)).dp)
                        .graphicsLayer {
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = percentPageOffset
                            )
                        },
                    activity = activities[page]
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
                activities.forEach { activity ->
                    ActivityCard(
                        modifier = Modifier.size(width = 300.dp, height = 470.dp),
                        activity = activity
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BlockTeam(windowWidth: Dp) {
    SharedTransitionLayout {
        val persons = listOf(
            Person(
                name = "Фоменко Анна",
                photo = Res.drawable.pers3,
                post = "Заведующая детского сада Алфавит",
                details = "Имеет высшее экономическое образование по специальности «Финансы и Кредит». Окончила КГСХА. Имеет большой опыт работы в образовательной сфере, в частности в школе Английского языка. Рада помочь вам в любых вопросах связанных с нашим садом."
            ),
            Person(
                name = "Кудрявцева Юлия",
                photo = Res.drawable.pers1,
                post = "Преподаватель начальных классов",
                details = "Проводит занятия «Подготовка к школе» как в группе, так и индивидуально. А также ведёт репетиторство по основным предметам начальной школы. Образование: 2 высших - ТМИ и ЮУрГУ. Имеет научную публикацию по теме детско-родительских отношений."
            ),
            Person(
                name = "Иванова Надежда",
                photo = Res.drawable.pers2,
                post = "Воспитатель старшей группы",
                details = "Имеет высшее педагогическое образование, богатейший опыт работы с детьми начальных классов. Стаж работы с детьми более 28 лет. Специалист широкого профиля."
            ),
            Person(
                name = "Васютинская Анна",
                photo = Res.drawable.pers4,
                post = "Воспитатель младшей группы",
                details = "Инструктор по физической культуре. Имеет высшее образование и 20-ти летний стаж работы в образовательной сфере. Для повышения эффективности образования использует нестандартные формы занятий: занятия по физическому воспитанию. В различных видах деятельный развивает в своих воспитанниках творческие и интеллектуальные способности, умение логически мыслить, проявлять инициативу и самостоятельность."
            ),
            Person(
                name = "Гусейнова Дурдана",
                photo = Res.drawable.pers5,
                post = "Помощник воспитателя",
                details = "Образование: среднее специальное. Стаж работы помощником воспитателя более 1 года."
            ),
            Person(
                name = "Сеидова Руфана",
                photo = Res.drawable.pers6,
                post = "Помощник воспитателя",
                details = "Образование: среднее. Стаж работы помощником воспитателя более 1 года."
            ),
        )
        var selectedPerson by remember { mutableStateOf(persons.first()) }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color(0xFF619B8A))
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
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
            Row(
                modifier = Modifier
                    .width(1100.dp)
                    .animateContentSize(),
            ) {
                persons.forEach { person ->
                    AnimatedVisibility(
                        visible = selectedPerson == person,
                    ) {
                        if (windowWidth < 920.dp) {
                            PersonDetailsPortraitCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animationVisibilityScope = this@AnimatedVisibility,
                                person = person,
                            )
                        } else {
                            PersonDetailsCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animationVisibilityScope = this@AnimatedVisibility,
                                person = person,
                            )
                        }
                    }
                }
            }
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier.horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.Center,
            ) {
                persons.forEach { person ->
                    AnimatedVisibility(
                        visible = selectedPerson != person,
                    ) {
                        PersonCard(
                            modifier = Modifier.width(210.dp),
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
