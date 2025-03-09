package ru.markn.alfavitweb.pres.components.shared

data class ServiceSharedKey(
    val title: String,
    val type: SharedElementType,
) {
    enum class SharedElementType {
        Bounds,
        Title,
        Price,
        Image,
    }
}
