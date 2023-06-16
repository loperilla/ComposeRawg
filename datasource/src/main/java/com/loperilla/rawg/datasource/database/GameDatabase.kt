package com.loperilla.rawg.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loperilla.rawg.datasource.database.dao.GameGenreDao
import com.loperilla.rawg.datasource.database.dao.QueryDao
import com.loperilla.rawg.datasource.database.entity.GenreEntity
import com.loperilla.rawg.datasource.database.entity.QueryEntity

@Database(
    entities = [
        GenreEntity::class,
        QueryEntity::class
    ],
    version = 1
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun genreDao(): GameGenreDao
    abstract fun queryDao(): QueryDao
}