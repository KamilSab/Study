@file:OptIn(ExperimentalFoundationApi::class)

package ru.itis.homework6.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit,
    onNavigateToAdd: () -> Unit,
    filmViewModel: FilmViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Коллекция фильмов") },
                actions = {
                    Button(onClick = onLogout) {
                        Text("Выйти")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить фильм")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(filmViewModel.films) { film ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .combinedClickable(
                            onClick = { /* Можно реализовать открытие деталей */ },
                            onLongClick = { filmViewModel.removeFilm(film) }
                        )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = film.title, style = MaterialTheme.typography.headlineLarge)
                        Text(text = "Режиссер: ${film.director}")
                        Text(text = "Год: ${film.year}")
                        Text(text = "Оценка: ${film.rating}")
                    }
                }
            }
        }
    }
}