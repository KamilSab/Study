package ru.itis.homework3.model

import android.os.Parcelable

data class Question(
    var id: Int,
    val questionText: String,
    val answers: List<String>,
    var selectedAnswer: Int = -1
)