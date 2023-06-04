package com.loperilla.rawg.datasource.network.api

import com.loperilla.rawg.datasource.network.model.CreatorResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse

interface CreatorsApi {
    suspend fun getAllPlatforms(): BaseResponse<CreatorResponse>
    suspend fun getPlatformDetail(id: Int): BaseResponse<CreatorResponse>
}
