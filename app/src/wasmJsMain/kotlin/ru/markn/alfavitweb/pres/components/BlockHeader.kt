package ru.markn.alfavitweb.pres.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.alfavit
import alfavit_web.app.generated.resources.vk
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.HeaderItem
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.BlockHeader(
    modifier: Modifier = Modifier,
    state: MainUIState,
    blockList: LazyListState,
) {
    with(rememberCoroutineScope()) {
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
                if (!state.isMobileVersion) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BlockHeaderItem(
                            headerItem = HeaderItem.About,
                            blockList = blockList,
                        )
                        BlockHeaderItem(
                            headerItem = HeaderItem.Services,
                            blockList = blockList,
                        )
                        BlockHeaderItem(
                            headerItem = HeaderItem.Contacts,
                            blockList = blockList,
                        )
                        BlockHeaderItem(
                            headerItem = HeaderItem.InfoOrganisation,
                            blockList = blockList,
                        )
                    }
                    Icon(
                        painter = painterResource(Res.drawable.vk),
                        contentDescription = "VK Icon",
                        tint = Color(0xFF233D4D),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(46.dp)
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = ::onVkLinkPressed
                            ),
                    )
                } else {
                    Crossfade(targetState = state.isMobileMenuOpened) { isMenuOpened ->
                        Icon(
                            imageVector = if (isMenuOpened) Icons.Default.Close else Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            modifier = Modifier
                                .padding(12.dp)
                                .size(46.dp)
                                .clip(CircleShape)
                                .pointerHoverIcon(PointerIcon.Hand)
                                .clickable(onClick = { onMobileMenuChange(!isMenuOpened) }),
                        )
                    }
                }
            }
            AnimatedVisibility(visible = state.isMobileMenuOpened) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                ) {
                    BlockMobileHeaderItem(
                        headerItem = HeaderItem.About,
                        blockList = blockList,
                    )
                    BlockMobileHeaderItem(
                        headerItem = HeaderItem.Services,
                        blockList = blockList,
                    )
                    BlockMobileHeaderItem(
                        headerItem = HeaderItem.Contacts,
                        blockList = blockList,
                    )
                    BlockMobileHeaderItem(
                        headerItem = HeaderItem.InfoOrganisation,
                        blockList = blockList,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(68.dp)
                            .clickable(onClick = ::onVkLinkPressed)
                            .pointerHoverIcon(PointerIcon.Hand),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.vk),
                            contentDescription = "VK Icon",
                            tint = Color(0xFF233D4D),
                            modifier = Modifier.size(46.dp),
                        )
                    }
                }
            }
        }
    }
}

context(coroutineScope: CoroutineScope)
@Composable
private fun IMainActions.BlockHeaderItem(
    headerItem: HeaderItem,
    blockList: LazyListState,
) {
    TextButton(
        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
        onClick = {
            headerItem.block?.let {
                coroutineScope.launch {
                    blockList.animateScrollToItem(it.ordinal, scrollOffset = -72)
                }
            }
            if (headerItem == HeaderItem.InfoOrganisation) {
                onInfoOrganizationMenuChange(true)
            }
        },
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
    ) {
        Text(
            text = headerItem.title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppTheme.FontFamily,
            )
        )
    }
}

context(coroutineScope: CoroutineScope)
@Composable
private fun IMainActions.BlockMobileHeaderItem(
    headerItem: HeaderItem,
    blockList: LazyListState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable {
                onMobileMenuChange(false)
                headerItem.block?.let {
                    coroutineScope.launch {
                        blockList.scrollToItem(it.ordinal, scrollOffset = -68)
                    }
                }
                if (headerItem == HeaderItem.InfoOrganisation) {
                    onInfoOrganizationMenuChange(true)
                }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = headerItem.title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppTheme.FontFamily,
            )
        )
    }
}