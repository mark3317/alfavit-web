package ru.markn.alfavitweb.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class Person(
    val name: String,
    val photo: DrawableResource,
    val post: String,
    val details: String,
)
