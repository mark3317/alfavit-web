package ru.markn.alfavitweb.pres.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.alfavit_place
import alfavit_web.app.generated.resources.vk
import alfavit_web.app.generated.resources.yandex_maps
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.BlockContacts(state: MainUIState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Наши контакты",
            style = TextStyle(
                fontSize = 46.sp,
                fontFamily = AppTheme.FontFamily,
            )
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 42.dp)
                .widthIn(1000.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(36.dp),
            ) {
                Text(
                    text = "Мы в Тюмени",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = "Позвоните нам",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(text = "+7 (912) 397-03-40")
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = "Наш адрес",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(text = "ул. Федюнинского, д. 56к2")
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = "Режим работы",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(text = "Пн-Пт 07:30 - 19:00")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.vk),
                        contentDescription = "VK Icon",
                        tint = Color(0xFF233D4D),
                        modifier = Modifier
                            .size(46.dp)
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = ::onVkLinkPressed
                            ),
                    )
                    Icon(
                        painter = painterResource(Res.drawable.yandex_maps),
                        contentDescription = "Yandex Maps Icon",
                        tint = Color(0xFF233D4D),
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = ::onYandexCardPressed
                            ),
                    )
                }
            }
            if (!state.isMobileVersion) {
                ElevatedCard(
                    modifier = Modifier
                        .size(width = 600.dp, height = 420.dp)
                        .pointerHoverIcon(PointerIcon.Hand),
                    shape = RoundedCornerShape(20),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp,
                        hoveredElevation = 16.dp,
                    ),
                    onClick = ::onYandexCardPressed
                ) {
                    Image(
                        painter = painterResource(Res.drawable.alfavit_place),
                        contentDescription = "Kubiki Image",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}