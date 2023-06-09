package com.loperilla.rawg.datasource.network.api

import com.loperilla.rawg.datasource.network.model.genre.GenreResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse

interface GameGenreApi {
    suspend fun getAllGenres(): BaseResponse<GenreResponse>
}