package ru.markn.alfavitweb.domain.models

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.service1
import alfavit_web.app.generated.resources.service2
import alfavit_web.app.generated.resources.service3
import alfavit_web.app.generated.resources.service4
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

enum class Service(
    val image: DrawableResource,
    val color: Color,
    val title: String,
    val description: String,
    val price: String,
) {
    FullDayGroup(
        image = Res.drawable.service1,
        title = "Группа полного дня",
        color = Color(0xFFFE7F2D),
        description = "Группа полного дня с 07:30 до 19:00\n" +
                "\n" +
                "В стоимость входит:\n" +
                "- Уход и присмотр за детьми;\n" +
                "- 5-ти разовое вкусное питание;\n" +
                "- Развивающие и творческие занятия;\n" +
                "- Прогулки на свежем воздухе;\n" +
                "- Тематические мероприятия.\n" +
                "\n" +
                "Младшая группа с 1 года до 3-х лет.\n" +
                "Старшая группа с 4-х лет до 6-7 лет (с уклоном на подготовку к школе: обучение счёту, письму, чтению,окружающему миру).",
        price = "19500 ₽"
    ),
    ShortDayGroup(
        image = Res.drawable.service2,
        title = "Группа неполного дня",
        color = Color(0xFFFCCA46),
        description = "Группа неполного дня с 07:30 до 15:00.\n" +
                "\n" +
                "В стоимость входит:\n" +
                "- Уход и присмотр за детьми;\n" +
                "- Питание;\n" +
                "- Развивающие и творческие занятия;\n" +
                "- Прогулки на свежем воздухе;\n" +
                "- Тематические мероприятия.\n" +
                "\n" +
                "Младшая группа с 1 года до 3-х лет.\n" +
                "Старшая группа с 4-х лет до 6-7 лет (с уклоном на подготовку к школе: обучение счёту, письму, чтению,окружающему миру).",
        price = "15000 ₽"
    ),
    OneVisit(
        image = Res.drawable.service3,
        title = "Единоразовое посещение",
        color = Color(0xFF619B8A),
        description = "Единоразовое посещение детского сада без покупки абонемента (к примеру, раз/два в неделю).\n" +
                "\n" +
                "В стоимость входит:\n" +
                "- Уход и присмотр за детьми;\n" +
                "- Питание;\n" +
                "- Развивающие и творческие занятия;\n" +
                "- Прогулки на свежем воздухе;\n" +
                "- Тематические мероприятия.\n" +
                "\n" +
                "Младшая группа с 1 года до 3-х лет.\n" +
                "Старшая группа с 4-х лет до 6-7 лет с уклоном на подготовку к школе: обучение письму, чтению, счёту, окружающему миру и т.д.",
        price = "1300 ₽"
    ),
    Adaptation(
        image = Res.drawable.service4,
        title = "Адаптация",
        description = "Пробное посещение детского сада на 1 час для комфортного знакомства ребенка с новой обстановкой.",
        color = Color(0xFF37AE48),
        price = "250 ₽"
    ),
}
