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

    override fun onMobileMenuPressed() {
        updateState {
            copy(isMobileMenuOpened = !isMobileMenuOpened)
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

    override fun onAppServicesPressed(sender: Service) {
        when(sender) {
            Service.FullDayGroup -> window.open("https://vk.com/market/product/gruppa-polnogo-dnya-226724376-9642454")
            Service.ShortDayGroup -> window.open("https://vk.com/market/product/gruppa-nepolnogo-dnya-226724376-9642544")
            Service.OneVisit -> window.open("https://vk.com/market/product/edinorazovoe-poseschenie-226724376-9642565")
            Service.Adaptation -> window.open("https://vk.com/market/product/adaptatsia-226724376-9642577")
        }
    }

    override fun onVkLinkPressed() {
        window.open("https://vk.com/club226724376")
    }

    override fun onYandexCardPressed() {
        window.open("https://yandex.ru/maps/org/alfavit/212154210185?si=0pjt1q0306p9xbrctt7665ta0m")
    }
}