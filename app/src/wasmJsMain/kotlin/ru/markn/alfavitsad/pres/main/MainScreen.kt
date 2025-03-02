package ru.markn.alfavitsad.pres.main

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.home
import alfavit_web.app.generated.resources.kubiki
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import ru.markn.alfavitsad.pres.utils.components.ActivityCard
import ru.markn.alfavitsad.pres.utils.components.AppHeader
import ru.markn.alfavitsad.pres.utils.components.AppTheme
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
            SharedTransitionLayout(
                modifier = Modifier
                    .height(700.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFF619B8A)),
            ) {
                var cardSelected by remember { mutableStateOf(0) }
                var cardList = listOf(0, 1, 2, 3)

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedContent(
                        targetState = cardSelected,
                    ) {
                        Box(
                            modifier = Modifier
                                .sharedBounds(
                                    rememberSharedContentState(key = cardSelected),
                                    animatedVisibilityScope = this@AnimatedContent,
                                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                                )
                                .fillMaxWidth()
                                .height(400.dp)
                                .padding(32.dp),
                        ) {
                            ExpandedInfoCard(
                                modifier = Modifier.matchParentSize(),
                                text = "Карта $cardSelected",
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        cardList.forEach { card ->
                            AnimatedVisibility(
                                visible = card != cardSelected,
                                enter = fadeIn(),
                                exit = fadeOut(),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = card),
                                            animatedVisibilityScope = this@AnimatedVisibility,
                                            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                                        )
                                        .clickable {
                                            cardSelected = card
                                        }
                                ) {
                                    InfoCard(
                                        modifier = Modifier.matchParentSize(),
                                        text = "Карта $card",
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    text: String,
) {
        ElevatedCard(
            modifier = modifier,
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                )
            }
        }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ExpandedInfoCard(
    modifier: Modifier = Modifier,
    text: String,
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text
            )
        }
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
