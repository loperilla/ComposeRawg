package com.loperilla.rawg.data.repository

import com.loperilla.rawg.datasource.network.api.CreatorsApi
import com.loperilla.rawg.datasource.network.model.response.ErrorResponse
import com.loperilla.rawg.datasource.network.model.response.SuccessResponse
import com.loperilla.rawg.model.creator.Creator
import com.loperilla.rawg.model.domain.ResultResponse
import javax.inject.Inject

class CreatorRepository @Inject constructor(
    private val creatorsApi: CreatorsApi
) {
    suspend fun getAllCreators(): ResultResponse<List<Creator>> {
        return when (val result = creatorsApi.getAllPlatforms()) {
            is ErrorResponse -> {
                ResultResponse.Error(result.errorMessage)
            }

            is SuccessResponse -> {
                ResultResponse.Success(
                    result.data.results.map {
                        it.toDomain()
                    }
                )
            }
        }
    }
}