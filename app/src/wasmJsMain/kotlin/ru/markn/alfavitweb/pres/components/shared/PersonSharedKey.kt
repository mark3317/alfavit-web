package ru.markn.alfavitweb.pres.components.shared

data class PersonSharedKey(
    val name: String,
    val type: SharedElementType,
) {
    enum class SharedElementType {
        Bounds,
        Name,
        Photo,
    }
}