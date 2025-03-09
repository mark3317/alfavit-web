package ru.markn.alfavitweb.domain.models

enum class InfoOrganization(
    val title: String,
    val link: String,
) {
    Charter("Устав", "https://disk.yandex.ru/i/rmk8Lasds570kA"),
    Certificate("Свидетельство о государственной регистрации", "https://disk.yandex.ru/i/NXmE70XYQpa1Sw"),
    Conclusion("Санитарно-эпидемиологическое заключение", "https://disk.yandex.ru/i/XkjicxY9D2oE5Q"),
    Extract("Выписка из реестра лицензии", "https://disk.yandex.ru/i/WDe9OQpbw9QF7Q")
}