package ru.markn.alfavitsad.pres.main

import alfavit_web.app.generated.resources.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun IMainActions.MainScreen(state: MainUIState) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val windowWidth = maxWidth
        val windowHeight = maxHeight
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
                    painter = painterResource(Res.drawable.img2),
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
                        modifier = Modifier
                            .width(windowWidth * 0.7f)
                            .clickable(
                                interactionSource = null,
                                indication = null
                            ) {
                                coroutineScope.launch {
                                    scrollState.animateScrollTo(scrollState.maxValue)
                                }
                            },
                        text = state.title,
                        maxLines = 2,
                        autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 90.sp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
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
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFFA1C181)),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    modifier = Modifier.padding(100.dp),
                    text = state.title,
                    autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 200.sp),
                    maxLines = 1
                )
            }
            BasicText(
                text = "windowWidth: $windowWidth, windowHeight: $windowHeight",
                autoSize = TextAutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 20.sp),
            )
        }
        AppHeader(modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.alfavit),
            contentDescription = "Alfavit Ico",
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                modifier = Modifier.padding(10.dp),
                text = "О нас",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                )
            )
            BasicText(
                modifier = Modifier.padding(10.dp),
                text = "Тарифы",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                )
            )
            BasicText(
                modifier = Modifier.padding(10.dp),
                text = "Контакты",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                )
            )
        }
        BasicText(
            modifier = Modifier.padding(10.dp),
            text = "Header",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
            )
        )
    }
}

val fontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.reminder_medium),
        Font(Res.font.reminder_bold),
        Font(Res.font.reminder_regular),
    )