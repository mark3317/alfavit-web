package ru.markn.alfavitweb.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class Activity(
    val image: DrawableResource,
    val title: String,
    val description: String,
)
