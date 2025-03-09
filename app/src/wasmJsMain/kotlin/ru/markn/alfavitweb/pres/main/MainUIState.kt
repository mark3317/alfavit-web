package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import ru.markn.alfavitweb.domain.models.Activity
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.utils.mvi.IMviState

data class MainUIState(
    val window: Window = Window(
        width = Dp.Unspecified,
        height = Dp.Unspecified,
        isMobileVersion = false
    ),
    val isMobileMenuOpened: Boolean = false,
    val isInfoOrganizationOpened: Boolean = false,
    val activitySelected: Activity = Activity.Activity1,
    val personSelected: Person = Person.Person1,
    val serviceSelected: Service? = null,
) : IMviState {
    data class Window(
        val width: Dp,
        val height: Dp,
        val isMobileVersion: Boolean,
    )
}
