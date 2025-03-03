package ru.markn.alfavitsad.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class Person(
    val name: String,
    val photo: DrawableResource,
    val details: String,
)
