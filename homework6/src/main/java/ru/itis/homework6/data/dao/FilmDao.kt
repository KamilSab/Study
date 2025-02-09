package ru.itis.homework6.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.homework6.data.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity): Long

    @Delete
    suspend fun deleteFilm(film: FilmEntity)
}