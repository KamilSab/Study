package ru.itis.homework2.model

import android.os.Parcelable

data class ListGameItemModel(
    val id: Int,
    val imageUrl: String,
    val gameName: String,
    val description: String
)