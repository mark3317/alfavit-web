package ru.markn.alfavitweb.pres.main

import org.koin.android.annotation.KoinViewModel
import ru.markn.alfavitweb.pres.utils.mvi.MviViewModel

@KoinViewModel
class MainProcessor: IMainActions, MviViewModel<MainUIState>(
    MainUIState()
) {

}