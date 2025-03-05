package ru.markn.alfavitweb.pres.main

import ru.markn.alfavitweb.pres.utils.mvi.IMviState

data class MainUIState(
    val title: String = "Детский сад «Алфавит»",
) : IMviState
