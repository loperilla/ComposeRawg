package com.loperilla.rawg.data.repository

import com.loperilla.rawg.datasource.database.dao.GameGenreDao
import com.loperilla.rawg.datasource.network.api.GameGenreApi
import com.loperilla.rawg.datasource.network.model.response.ErrorResponse
import com.loperilla.rawg.datasource.network.model.response.SuccessResponse
import com.loperilla.rawg.model.game.detail.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val genreApi: GameGenreApi,
    private val genreDao: GameGenreDao
) {
    suspend fun getAllGenre(): Flow<List<Genre>> = flow {
        val entityList = genreDao.getAllGenre()
        if (entityList.isNotEmpty()) emit(entityList.map { it.toDomain() })
        when (val response = genreApi.getAllGenres()) {
            is ErrorResponse -> TODO()
            is SuccessResponse -> {
                val genreResults = response.data.results
                genreDao.insertAllGenre(genreResults.map {
                    it.toEntity()
                })
                emit(genreResults.map { it.toDomain() })
            }
        }
    }
}