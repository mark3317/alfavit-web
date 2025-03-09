package ru.markn.alfavitweb.pres.main

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import org.koin.android.annotation.KoinViewModel
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.utils.mvi.MviViewModel

@KoinViewModel
class MainProcessor : IMainActions, MviViewModel<MainUIState>(
    MainUIState()
) {
    override fun windowSizeChange(width: Dp, height: Dp) {
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

    override fun onMobileMenuChange(isOpened: Boolean) {
        updateState {
            copy(isMobileMenuOpened = isOpened)
        }
    }

    override fun onInfoOrganizationMenuChange(isOpened: Boolean) {
        updateState {
            copy(isInfoOrganizationOpened = isOpened)
        }
    }

    override fun onPersonPressed(person: Person) {
        updateState {
            copy(personSelected = person)
        }
    }

    override fun onServicePressed(service: Service) {
        updateState {
            copy(serviceSelected = service)
        }
    }

    override fun onOutsideServicePressed() {
        updateState {
            copy(serviceSelected = null)
        }
    }

    override fun onLinkPressed(link: String) {
        window.open(link)
    }

    override fun onVkLinkPressed() {
        window.open("https://vk.com/club226724376")
    }

    override fun onYandexCardPressed() {
        window.open("https://yandex.ru/maps/org/alfavit/212154210185?si=0pjt1q0306p9xbrctt7665ta0m")
    }
}