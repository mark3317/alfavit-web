package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.utils.mvi.IMviActions

interface IMainActions : IMviActions {
    fun windowSizeChange(width: Dp, height: Dp)
    fun onMobileMenuChange(isOpened: Boolean)
    fun onInfoOrganizationMenuChange(isOpened: Boolean)
    fun onPersonPressed(person: Person)
    fun onServicePressed(service: Service)
    fun onOutsideDialogPressed()
    fun onLinkPressed(link: String)
    fun onVkLinkPressed()
    fun onYandexCardPressed()
}