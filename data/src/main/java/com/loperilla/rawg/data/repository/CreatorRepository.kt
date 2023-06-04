package com.loperilla.rawg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.loperilla.rawg.data.paging.CreatorPagingSource
import com.loperilla.rawg.datasource.network.api.CreatorsApi
import javax.inject.Inject

class CreatorRepository @Inject constructor(
    private val creatorsApi: CreatorsApi
) {
//    suspend fun getAllCreators(): ResultResponse<List<Creator>> {
//        return when (val result = creatorsApi.getAllPlatforms()) {
//            is ErrorResponse -> {
//                ResultResponse.Error(result.errorMessage)
//            }
//
//            is SuccessResponse -> {
//                ResultResponse.Success(
//                    result.data.results.map {
//                        it.toDomain()
//                    }
//                )
//            }
//        }
//    }

    fun getAllCreators() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            CreatorPagingSource(creatorsApi)
        }
    ).flow
}