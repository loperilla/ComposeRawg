package com.loperilla.rawg.datasource.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// Clase base sellada para las respuestas
sealed class BaseResponse<out T>

// Clase para respuesta correcta
data class SuccessResponse<T>(
    val data: ApiResponse<T>
) : BaseResponse<T>()

// Clase para respuesta fallida
data class ErrorResponse(
    val errorMessage: String
) : BaseResponse<Nothing>()

@Serializable
data class ApiResponse<T>(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<T>
)