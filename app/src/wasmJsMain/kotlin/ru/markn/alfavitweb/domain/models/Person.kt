package ru.markn.alfavitweb.domain.models

import alfavit_web.app.generated.resources.Res
import alfavit_web.app.generated.resources.pers1
import alfavit_web.app.generated.resources.pers2
import alfavit_web.app.generated.resources.pers3
import alfavit_web.app.generated.resources.pers4
import alfavit_web.app.generated.resources.pers5
import alfavit_web.app.generated.resources.pers6
import org.jetbrains.compose.resources.DrawableResource

enum class Person(
    val fullName: String,
    val photo: DrawableResource,
    val post: String,
    val details: String,
) {
    Person1(
        fullName = "Фоменко Анна",
        photo = Res.drawable.pers3,
        post = "Заведующая детского сада Алфавит",
        details = "Имеет высшее экономическое образование по специальности «Финансы и Кредит». Окончила КГСХА. Имеет большой опыт работы в образовательной сфере, в частности в школе Английского языка. Рада помочь вам в любых вопросах связанных с нашим садом."
    ),
    Person2(
        fullName = "Кудрявцева Юлия",
        photo = Res.drawable.pers1,
        post = "Преподаватель начальных классов",
        details = "Проводит занятия «Подготовка к школе» как в группе, так и индивидуально. А также ведёт репетиторство по основным предметам начальной школы. Образование: 2 высших - ТМИ и ЮУрГУ. Имеет научную публикацию по теме детско-родительских отношений."
    ),
    Person3(
        fullName = "Иванова Надежда",
        photo = Res.drawable.pers2,
        post = "Воспитатель старшей группы",
        details = "Имеет высшее педагогическое образование, богатейший опыт работы с детьми начальных классов. Стаж работы с детьми более 28 лет. Специалист широкого профиля."
    ),
    Person4(
        fullName = "Васютинская Анна",
        photo = Res.drawable.pers4,
        post = "Воспитатель младшей группы",
        details = "Инструктор по физической культуре. Имеет высшее образование и 20-ти летний стаж работы в образовательной сфере. Для повышения эффективности образования использует нестандартные формы занятий: занятия по физическому воспитанию. В различных видах деятельный развивает в своих воспитанниках творческие и интеллектуальные способности, умение логически мыслить, проявлять инициативу и самостоятельность."
    ),
    Person5(
        fullName = "Гусейнова Дурдана",
        photo = Res.drawable.pers5,
        post = "Помощник воспитателя",
        details = "Образование: среднее специальное. Стаж работы помощником воспитателя более 1 года."
    ),
    Person6(
        fullName = "Сеидова Руфана",
        photo = Res.drawable.pers6,
        post = "Помощник воспитателя",
        details = "Образование: среднее. Стаж работы помощником воспитателя более 1 года."
    ),
}
