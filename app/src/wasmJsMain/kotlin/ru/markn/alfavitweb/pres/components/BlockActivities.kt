package ru.markn.alfavitweb.pres.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import ru.markn.alfavitweb.domain.models.Activity
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme
import kotlin.math.absoluteValue

@Composable
fun BlockActivities(state: MainUIState) {
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
        if (state.window.isMobileVersion) {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = Activity.entries::size)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(520.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = state.window.width / 2 - 150.dp),
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
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        }
                        .pointerHoverIcon(PointerIcon.Hand),
                    activity = Activity.entries[page]
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
                horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(36.dp),
                maxItemsInEachRow = 3
            ) {
                Activity.entries.forEach { activity ->
                    ActivityCard(
                        modifier = Modifier.size(width = 300.dp, height = 470.dp),
                        activity = activity
                    )
                }
            }
        }
    }
}