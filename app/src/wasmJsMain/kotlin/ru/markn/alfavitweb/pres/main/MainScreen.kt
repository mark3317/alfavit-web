package ru.markn.alfavitweb.pres.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.unit.dp
import ru.markn.alfavitweb.domain.models.Block
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.components.*
import ru.markn.alfavitweb.pres.utils.AppTheme

context(sharedTransitionScope: SharedTransitionScope)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IMainActions.MainScreen(state: MainUIState) {
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val font = AppTheme.FontFamily
    LaunchedEffect(fontFamilyResolver) {
        fontFamilyResolver.preload(font)
    }
    val blockList = rememberLazyListState()
    BoxWithConstraints(
        modifier = Modifier.then(
            when (state.isDialogOpened) {
                true -> Modifier.blur(16.dp)
                false -> Modifier
            }
        )
    ) {
        if (state.window.width != maxWidth || state.window.height != maxHeight) {
            windowSizeChange(maxWidth, maxHeight)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    when (state.isMobileMenuOpened) {
                        true -> Modifier.blur(16.dp)
                        false -> Modifier
                    }
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
        AnimatedBackgroundBox(state.isMobileMenuOpened)
        BlockHeader(
            state = state,
            blockList = blockList,
        )
    }
    AnimatedBackgroundBox(state.isDialogOpened)
    Service.entries.forEach { service ->
        AnimatedDialog(visible = state.serviceSelected == service) {
            ServiceDetailsCard(
                modifier = Modifier.sizeIn(
                    maxWidth = state.window.width * 0.8f,
                    maxHeight = state.window.height * 0.9f
                ),
                service = service,
            )
        }
    }
    AnimatedDialog(visible = state.isInfoOrganizationOpened) {
        InfoOrganizationCard(state)
    }
}

@Composable
private fun IMainActions.AnimatedBackgroundBox(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = ::onOutsideDialogPressed
                )
        )
    }
}

@Composable
private fun AnimatedDialog(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
