package ru.markn.alfavitweb.pres.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.kubiki
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.pres.utils.AppTheme

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
