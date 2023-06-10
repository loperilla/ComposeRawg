package com.loperilla.rawg.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loperilla.rawg.datasource.network.NetworkUtils.getPageValue
import com.loperilla.rawg.datasource.network.api.GameApi
import com.loperilla.rawg.datasource.network.model.response.ErrorResponse
import com.loperilla.rawg.datasource.network.model.response.SuccessResponse
import com.loperilla.rawg.model.game.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GamePagingSource @Inject constructor(
    private val gameApi: GameApi
) : PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return withContext(Dispatchers.IO) {
            val currentPage = params.key ?: 0
            try {
                when (val result = gameApi.getGameList(currentPage)) {
                    is ErrorResponse -> {
                        LoadResult.Error(IOException(result.errorMessage))
                    }

                    is SuccessResponse -> {
                        LoadResult.Page(
                            data = result.data.results.map {
                                it.toDomain()
                            },
                            prevKey =
                            if (result.data.previous != null || currentPage == 0) {
                                null
                            } else {
                                result.data.previous?.getPageValue()
                            },
                            nextKey = if (result.data.next == null || result.data.results.isEmpty()) {
                                null
                            } else {
                                result.data.next?.getPageValue()
                            }
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                LoadResult.Error(e)
            }
        }
    }

}