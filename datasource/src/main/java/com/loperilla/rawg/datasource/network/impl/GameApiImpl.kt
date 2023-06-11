package com.loperilla.rawg.datasource.network.impl

import com.loperilla.rawg.datasource.BuildConfig
import com.loperilla.rawg.datasource.network.NetworkConstants
import com.loperilla.rawg.datasource.network.NetworkUtils.processResponse
import com.loperilla.rawg.datasource.network.api.GameApi
import com.loperilla.rawg.datasource.network.model.game.GameResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GameApiImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val json: Json
) : GameApi {
    override suspend fun getGameList(page: Int, query: String): BaseResponse<GameResponse> {
        return processResponse(
            json
        ) {
            httpClient.get {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url("${NetworkConstants.BASE_URL}${NetworkConstants.GAMES}")
                    parameters.append(NetworkConstants.API_KEY_NAME, BuildConfig.API_KEY)
                    if (page != 0) {
                        parameter("page", page)
                    }
                    if (query.isNotEmpty()) {
                        parameter("search", query)
                    }
                }
            }
        }
    }
}