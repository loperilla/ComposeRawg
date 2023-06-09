package com.loperilla.rawg.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loperilla.rawg.datasource.database.dao.GameGenreDao
import com.loperilla.rawg.datasource.database.entity.GenreEntity

@Database(
    entities = [
        GenreEntity::class
    ],
    version = 1
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun genreDao(): GameGenreDao
}