package com.loperilla.rawg.datasource.network.api

import com.loperilla.rawg.datasource.network.model.game.GameResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse

interface GameApi {
    suspend fun getGameList(page: Int): BaseResponse<GameResponse>
}
