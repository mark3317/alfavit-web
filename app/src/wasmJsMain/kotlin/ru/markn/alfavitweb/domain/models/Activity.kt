package ru.markn.alfavitweb.domain.models

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.activity1
import alfavit_web.app.generated.resources.activity2
import alfavit_web.app.generated.resources.activity3
import alfavit_web.app.generated.resources.activity4
import alfavit_web.app.generated.resources.activity5
import alfavit_web.app.generated.resources.activity6
import org.jetbrains.compose.resources.DrawableResource

enum class Activity(
    val image: DrawableResource,
    val title: String,
    val description: String,
) {
    Activity1(
        image = Res.drawable.activity1,
        title = "Развивающие занятия",
        description = "Развитие речи, рисование, лепка, аппликация, математическое развитие, окружающий мир, музыка, физкультура"
    ),
    Activity2(
        image = Res.drawable.activity2,
        title = "Подготовка к школе",
        description = "Подготовка к школе, развитие речи, математическое развитие, окружающий мир, музыка, физкультура"
    ),
    Activity3(
        image = Res.drawable.activity3,
        title = "Развитие речи",
        description = "Развитие речи, логопедические занятия, подготовка к школе, математическое развитие, окружающий мир, музыка, физкультура"
    ),
    Activity4(
        image = Res.drawable.activity4,
        title = "Развитие логики",
        description = "Развитие логики, математическое развитие, окружающий мир, музыка, физкультура"
    ),
    Activity5(
        image = Res.drawable.activity5,
        title = "Развитие мелкой моторики",
        description = "Развитие мелкой моторики, математическое развитие, окружающий мир, музыка, физкультура"
    ),
    Activity6(
        image = Res.drawable.activity6,
        title = "Развитие музыкального слуха",
        description = "Развитие музыкального слуха, математическое развитие, окружающий мир, музыка, физкультура"
    ),
}
