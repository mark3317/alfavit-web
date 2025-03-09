package ru.markn.alfavitweb.pres.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.markn.alfavitweb.domain.models.Block
import ru.markn.alfavitweb.pres.components.*

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IMainActions.MainScreen(state: MainUIState) {
    SharedTransitionLayout {
        val blockList = rememberLazyListState()
        BoxWithConstraints {
            if (state.window.width != maxWidth || state.window.height != maxHeight) {
                windowSizeChange(maxWidth, maxHeight)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (state.isMobileMenuOpened) {
                            Modifier.blur(16.dp)
                        } else Modifier
                    )
            ) {
                LazyColumn(
                    state = blockList,
                    modifier = Modifier.matchParentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    items(items = Block.entries) { block ->
                        when (block) {
                            Block.Home -> BlockHome(state)
                            Block.About -> BlockAbout()
                            Block.Activities -> BlockActivities(state)
                            Block.Team -> BlockTeam(state)
                            Block.Services -> BlockServices(state)
                            Block.Contacts -> BlockContacts(state)
                        }
                    }
                }
            }
            Crossfade(
                targetState = (state.isMobileMenuOpened),
            ) {
                if (it) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onMobileMenuChange(false) }
                            )
                    )
                }
            }
            BlockHeader(
                state = state,
                blockList = blockList,
            )
        }
    }
}
