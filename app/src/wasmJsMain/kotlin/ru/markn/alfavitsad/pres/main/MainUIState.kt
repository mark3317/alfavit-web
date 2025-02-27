package ru.markn.alfavitsad.pres.main

import ru.markn.alfavitsad.pres.utils.mvi.IMviState

data class MainUIState(
    val title: String = "Детский сад «Алфавит»",
) : IMviState
