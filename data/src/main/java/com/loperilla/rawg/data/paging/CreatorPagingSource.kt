package com.loperilla.rawg.data.paging

//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.loperilla.rawg.datasource.network.api.CreatorsApi
//import com.loperilla.rawg.datasource.network.model.response.ErrorResponse
//import com.loperilla.rawg.datasource.network.model.response.SuccessResponse
//import com.loperilla.rawg.model.creator.Creator
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.io.IOException
//import javax.inject.Inject
//
//class CreatorPagingSource @Inject constructor(
//    private val creatorsApi: CreatorsApi
//) : PagingSource<Int, Creator>() {
//    override fun getRefreshKey(state: PagingState<Int, Creator>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Creator> {
//        return withContext(Dispatchers.IO) {
//            val currentPage = params.key ?: 0
//            try {
//                when (val result = creatorsApi.getAllPlatforms(currentPage)) {
//                    is ErrorResponse -> {
//                        LoadResult.Error(IOException(result.errorMessage))
//                    }
//
//                    is SuccessResponse -> {
//                        LoadResult.Page(
//                            data = result.data.results.map {
//                                it.toDomain()
//                            },
//                            prevKey =
//                            if (result.data.previous != null || currentPage == 0) {
//                                null
//                            } else {
//                                result.data.previous?.substringAfter("page=")?.toInt()
//                            },
//                            nextKey = if (result.data.next == null || result.data.results.isEmpty()) {
//                                null
//                            } else {
//                                result.data.next?.substringAfter("page=")?.toInt()
//                            }
//                        )
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                LoadResult.Error(e)
//            }
//        }
//    }
//
//}
