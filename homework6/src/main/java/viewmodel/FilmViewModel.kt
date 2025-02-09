package viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.homework6.data.AppDatabase
import ru.itis.homework6.data.entity.FilmEntity
import ru.itis.homework6.data.repository.AppRepository

class FilmViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val db = AppDatabase.getDatabase(context)
    private val repository = AppRepository(db)

    var films by mutableStateOf(listOf<FilmEntity>())
        private set

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch {
            films = repository.getFilms()
        }
    }

    fun addFilm(
        title: String,
        director: String,
        description: String,
        year: Int,
        rating: Float,
        onAdded: () -> Unit
    ) {
        viewModelScope.launch {
            val film = FilmEntity(title = title, director = director, description = description, year = year, rating = rating)
            repository.addFilm(film)
            loadFilms()
            onAdded()
        }
    }

    fun removeFilm(film: FilmEntity) {
        viewModelScope.launch {
            repository.deleteFilm(film)
            loadFilms()
        }
    }
}
