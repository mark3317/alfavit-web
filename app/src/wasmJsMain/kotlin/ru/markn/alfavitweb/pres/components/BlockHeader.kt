package ru.markn.alfavitweb.pres.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.alfavit
import alfavit_web.app.generated.resources.vk
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.Block
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.BlockHeader(
    modifier: Modifier = Modifier,
    state: MainUIState,
    listState: LazyListState,
) {
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.alfavit),
                contentDescription = "Alfavit Ico",
            )
            if (!state.window.isMobileVersion) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(Block.About.ordinal)
                            }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            text = "О нас",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppTheme.FontFamily,
                            )
                        )
                    }
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(Block.Services.ordinal)
                            }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            text = "Тарифы",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppTheme.FontFamily,
                            )
                        )
                    }
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(Block.Contacts.ordinal)
                            }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            text = "Контакты",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppTheme.FontFamily,
                            )
                        )
                    }
                    TextButton(
                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            text = "Сведения об ОО",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppTheme.FontFamily,
                            )
                        )
                    }
                }
                Icon(
                    painter = painterResource(Res.drawable.vk),
                    contentDescription = "VK Icon",
                    tint = Color(0xFF233D4D),
                    modifier = Modifier
                        .padding(12.dp)
                        .size(46.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = ::onVkLinkPressed
                        ),
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable(onClick = ::onMobileMenuPressed),
                )
            }
        }
        DropdownMenu(
            expanded = state.isMobileMenuOpened,
            onDismissRequest = ::onMobileMenuPressed,
        ) {
            DropdownMenuItem(
                onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(Block.About.ordinal)
                        onMobileMenuPressed()
                    }
                },
                text = {
                    Text("О нас")
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(Block.Services.ordinal)
                        onMobileMenuPressed()
                    }
                },
                text = {
                    Text("Тарифы")
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(Block.Contacts.ordinal)
                        onMobileMenuPressed()
                    }
                },
                text = {
                    Text("Контакты")
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                onClick = {
                    onMobileMenuPressed()
                },
                text = {
                    Text("Сведения об ОО")
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                onClick = {
                    onVkLinkPressed()
                    onMobileMenuPressed()
                },
                text = {
                    Text("Мы в ВКонтакте")
                }
            )
        }
    }
}