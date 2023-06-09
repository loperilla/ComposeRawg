package com.loperilla.rawg.datasource.network

import android.util.Log
import com.loperilla.rawg.datasource.network.model.response.ApiResponse
import com.loperilla.rawg.datasource.network.model.response.BaseResponse
import com.loperilla.rawg.datasource.network.model.response.ErrorResponse
import com.loperilla.rawg.datasource.network.model.response.SuccessResponse
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

object NetworkUtils {
    suspend inline fun <reified T> processResponse(
        json: Json,
        call: () -> HttpResponse
    ): BaseResponse<T> {
        return try {
            val httpResponse = call.invoke()
            val body = httpResponse.bodyAsText()
            Log.e("response", body)
            if (httpResponse.status.value in 200..299) {
                SuccessResponse(
                    data = json.decodeFromString<ApiResponse<T>>(
                        body
                    )
                )
            } else {
                ErrorResponse(
                    "HTTP error ${httpResponse.status.value}: ${httpResponse.status.description}"
                )
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            ErrorResponse(
                "HTTP error ${e.response.status.value}: ${e.response.status.description}"
            )
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            ErrorResponse("Bad Request")
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            ErrorResponse("Internal Server Error")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ErrorResponse("Unknown error")
        }
    }

    fun String.getPageValue(): Int? {
        val startIndex = this.indexOf("page=")
        if (startIndex == -1) return null

        val endIndex = this.indexOf('&', startIndex)
        val pageValue = if (endIndex != -1) {
            this.substring(startIndex + 5, endIndex)
        } else {
            this.substring(startIndex + 5)
        }

        return pageValue.toInt()

    }
}
