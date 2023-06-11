package com.loperilla.rawg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.loperilla.rawg.data.paging.GamePagingSource
import com.loperilla.rawg.datasource.network.api.GameApi
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameApi: GameApi
) {
    fun getGames(searchInputQuery: String) = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            GamePagingSource(gameApi, searchInputQuery)
        }
    ).flow
}
