package ru.markn.alfavitsad.pres.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitsad.domain.models.Activity

@Composable
fun ActivityCard(
    modifier: Modifier = Modifier,
    activity: Activity,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                painter = painterResource(activity.image),
                contentDescription = "Kubiki Image",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = activity.title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = AppTheme.FontFamily,
                    )
                )
                Text(
                    text = activity.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 28.sp,
                    )
                )
            }
        }
    }
}
