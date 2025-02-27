package ru.markn.alfavitsad.pres.main

import org.koin.android.annotation.KoinViewModel
import ru.markn.alfavitsad.pres.utils.mvi.MviViewModel

@KoinViewModel
class MainProcessor: IMainActions, MviViewModel<MainUIState>(
    MainUIState()
) {

}