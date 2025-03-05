package ru.markn.alfavitweb.domain.models

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class Service(
    val image: DrawableResource,
    val color: Color,
    val title: String,
    val price: Int,
)
