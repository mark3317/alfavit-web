package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import ru.markn.alfavitweb.pres.utils.mvi.IMviActions

interface IMainActions : IMviActions {
    fun windowSizeChanged(width: Dp, height: Dp)
}