package com.loperilla.rawg.datasource.network.impl

import com.loperilla.rawg.datasource.BuildConfig
import com.loperilla.rawg.datasource.network.NetworkConstants
import com.loperilla.rawg.datasource.network.NetworkUtils.processResponse
import com.loperilla.rawg.datasource.network.api.GameGenreApi
import com.loperilla.rawg.datasource.network.model.genre.GenreResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GenreApiImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val json: Json
) : GameGenreApi {
    override suspend fun getAllGenres(): BaseResponse<GenreResponse> {
        return processResponse(json) {
            httpClient.get {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url("${NetworkConstants.BASE_URL}${NetworkConstants.GENRES}")
                    parameters.append(NetworkConstants.API_KEY_NAME, BuildConfig.API_KEY)
                }
            }
        }
    }
}
