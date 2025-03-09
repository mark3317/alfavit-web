package ru.markn.alfavitweb.pres.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState

@Composable
fun IMainActions.ServiceDetailsCard(
    service: Service,
    state: MainUIState
) {
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .sizeIn(maxWidth = state.window.width * 0.8f, maxHeight = state.window.height * 0.9f)
            .wrapContentSize(),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        FlowRow(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(32.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            itemVerticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(service.image),
                contentDescription = "Service Image",
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(20))
                    .border(
                        width = 6.dp,
                        color = service.color,
                        shape = RoundedCornerShape(20)
                    ),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.widthIn(min = 300.dp, max = 500.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = service.title,
                    style = TextStyle(
                        fontSize = 24.sp,
                    )
                )
                Text(
                    text = service.description,
                    style = TextStyle(fontSize = 16.sp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = service.price,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Button(
                        onClick = { onLinkPressed(service.link) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = service.color,
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Подать заявку",
                            style = TextStyle(
                                fontSize = 16.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}