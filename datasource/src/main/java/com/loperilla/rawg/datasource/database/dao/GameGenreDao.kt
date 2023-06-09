package com.loperilla.rawg.datasource.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.loperilla.rawg.datasource.database.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameGenreDao {
    @Upsert
    fun insertAllGenre(genreList: List<GenreEntity>)

    @Query("SELECT * FROM GenreEntity")
    suspend fun getAllGenre(): List<GenreEntity>
}