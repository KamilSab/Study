package ru.itis.homework6.data.repository

import ru.itis.homework6.data.AppDatabase
import ru.itis.homework6.data.entity.FilmEntity
import ru.itis.homework6.data.entity.UserEntity

class AppRepository(private val db: AppDatabase) {
    private val userDao = db.userDao()
    private val filmDao = db.filmDao()

    suspend fun registerUser(username: String, email: String, password: String): Boolean {
        val existingByEmail = userDao.getUserByEmail(email)
        val existingByUsername = userDao.getUserByUsername(username)
        if (existingByEmail != null || existingByUsername != null) {
            return false
        }
        val newUser = UserEntity(username = username, email = email, password = password)
        userDao.insertUser(newUser)
        return true
    }

    suspend fun authenticateUser(identifier: String, password: String): Boolean {
        val user = userDao.getUserByIdentifier(identifier)
        return user != null && user.password == password
    }

    suspend fun addFilm(film: FilmEntity) {
        filmDao.insertFilm(film)
    }

    suspend fun getFilms(): List<FilmEntity> {
        return filmDao.getAllFilms()
    }

    suspend fun deleteFilm(film: FilmEntity) {
        filmDao.deleteFilm(film)
    }
}