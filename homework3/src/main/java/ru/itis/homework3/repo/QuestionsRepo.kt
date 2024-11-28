package ru.itis.homework3.repo

import ru.itis.homework3.model.Question

object QuestionsRepo {
    val questions = listOf(
        Question(
            id = 0,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 1,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 2,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 3,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 4,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 5,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 6,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 7,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 8,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 9,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
        Question(
            id = 10,
            questionText = "Какая планета в Солнечной системе является самой большой?",
            answers = listOf("Юпитер", "Сатурн", "Марс", "Земля")
        ),
    )

    fun getById(id: Int) = questions.find{it.id == id}
}