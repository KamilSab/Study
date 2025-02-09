package ru.itis.homework6.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import viewmodel.FilmViewModel

@Composable
fun AddFilmScreen(
    onFilmAdded: () -> Unit,
    filmViewModel: FilmViewModel
) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var yearText by remember { mutableStateOf("") }
    var ratingText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Режиссер") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = yearText,
            onValueChange = { yearText = it },
            label = { Text("Год выхода") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = ratingText,
            onValueChange = { ratingText = it },
            label = { Text("Оценка критиков") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val year = yearText.toIntOrNull()
                val rating = ratingText.toFloatOrNull()
                if (title.isBlank() || director.isBlank() || description.isBlank() || year == null || rating == null) {
                    errorMessage = "Заполните все поля корректно"
                } else {
                    filmViewModel.addFilm(title, director, description, year, rating) {
                        onFilmAdded()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить фильм")
        }
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}