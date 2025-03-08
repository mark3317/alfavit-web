package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.android.annotation.KoinViewModel
import ru.markn.alfavitweb.pres.utils.mvi.MviViewModel

@KoinViewModel
class MainProcessor : IMainActions, MviViewModel<MainUIState>(
    MainUIState()
) {
    override fun windowSizeChanged(width: Dp, height: Dp) {
        updateState {
            copy(
                window = MainUIState.Window(
                    width = width,
                    height = height,
                    isMobileVersion = width < 920.dp
                )
            )
        }
    }
}