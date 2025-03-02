package ru.markn.alfavitsad.pres.utils.components

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.reminder_bold
import alfavit_web.app.generated.resources.reminder_medium
import alfavit_web.app.generated.resources.reminder_regular
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font

object AppTheme {
    val FontFamily
        @Composable
        get() = FontFamily(
            Font(Res.font.reminder_medium),
            Font(Res.font.reminder_bold),
            Font(Res.font.reminder_regular),
        )
}