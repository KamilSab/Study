package ru.itis.homework6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.homework6.data.dao.FilmDao
import ru.itis.homework6.data.dao.UserDao
import ru.itis.homework6.data.entity.FilmEntity
import ru.itis.homework6.data.entity.UserEntity

@Database(entities = [UserEntity::class, FilmEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}