package ru.markn.alfavitweb.domain.models

enum class HeaderItem(
    val title: String,
    val block: Block? = null,
) {
    About("О нас", Block.Activities),
    Services("Тарифы", Block.Services),
    Contacts("Контакты", Block.Contacts),
    InfoOrganisation("Сведения об ОО"),
}