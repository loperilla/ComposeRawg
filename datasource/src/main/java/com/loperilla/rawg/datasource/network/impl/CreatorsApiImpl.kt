package com.loperilla.rawg.datasource.network.impl

import com.loperilla.rawg.datasource.BuildConfig
import com.loperilla.rawg.datasource.network.NetworkConstants
import com.loperilla.rawg.datasource.network.NetworkConstants.BASE_URL
import com.loperilla.rawg.datasource.network.NetworkConstants.CREATORS
import com.loperilla.rawg.datasource.network.NetworkUtils.processResponse
import com.loperilla.rawg.datasource.network.api.CreatorsApi
import com.loperilla.rawg.datasource.network.model.CreatorResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CreatorsApiImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val json: Json
) : CreatorsApi {
    override suspend fun getAllPlatforms(): BaseResponse<CreatorResponse> {
        return processResponse(
            json
        ) {
            httpClient.get {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url("$BASE_URL$CREATORS")
                    parameters.append(NetworkConstants.API_KEY_NAME, BuildConfig.API_KEY)
                }
            }
        }
    }

    override suspend fun getPlatformDetail(id: Int): BaseResponse<CreatorResponse> {
        return processResponse(
            json
        ) {
            httpClient.get {
                url("$BASE_URL/$CREATORS/$id")
            }
        }
    }

}
