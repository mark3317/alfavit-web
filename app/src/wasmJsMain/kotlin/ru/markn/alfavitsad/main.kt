package ru.markn.alfavitsad

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import ru.markn.alfavitsad.pres.main.MainProcessor
import ru.markn.alfavitsad.pres.main.MainScreen
import ru.markn.alfavitsad.pres.utils.mvi.MviScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(defaultModule)
    }
    ComposeViewport(document.body!!) {
        App()
    }
}

@Preview
@Composable
fun App() {
    MaterialTheme {
        MviScreen(vm = koinViewModel<MainProcessor>()) {
            MainScreen(it)
        }
    }
}