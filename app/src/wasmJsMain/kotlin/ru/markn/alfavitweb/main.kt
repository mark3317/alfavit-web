package ru.markn.alfavitweb

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.ksp.generated.defaultModule
import ru.markn.alfavitweb.pres.main.MainProcessor
import ru.markn.alfavitweb.pres.main.MainScreen
import ru.markn.alfavitweb.pres.utils.mvi.MviScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication({
            defaultModule()
        }) {
            MaterialTheme {
                MviScreen(vm = koinViewModel<MainProcessor>()) {
                    MainScreen(it)
                }
            }
        }
    }
}
