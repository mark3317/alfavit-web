package ru.markn.alfavitsad.pres.utils.components

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
