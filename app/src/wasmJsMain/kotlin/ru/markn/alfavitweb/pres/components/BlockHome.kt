package ru.markn.alfavitweb.pres.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.BlockHome(state: MainUIState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(state.window.height)
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