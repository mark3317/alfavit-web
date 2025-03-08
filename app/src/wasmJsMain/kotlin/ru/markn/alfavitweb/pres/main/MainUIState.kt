package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import ru.markn.alfavitweb.pres.utils.mvi.IMviState

data class MainUIState(
    val window: Window = Window(
        width = Dp.Unspecified,
        height = Dp.Unspecified,
        isMobileVersion = false
    ),
    val title: String = "Детский сад «Алфавит»",
) : IMviState {
    data class Window(
        val width: Dp,
        val height: Dp,
        val isMobileVersion: Boolean,
    )
}
