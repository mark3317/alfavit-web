package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.utils.mvi.IMviActions

interface IMainActions : IMviActions {
    fun windowSizeChange(width: Dp, height: Dp)
    fun onMobileMenuOpened(status: Boolean)
    fun onPersonPressed(person: Person)
    fun onServicePressed(service: Service)
    fun onOutsideServicePressed()
    fun onAppServicesPressed(sender: Service)
    fun onVkLinkPressed()
    fun onYandexCardPressed()
}