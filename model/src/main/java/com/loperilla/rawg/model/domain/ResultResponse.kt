package com.loperilla.rawg.model.domain

sealed class ResultResponse<T> {
    data class Success<T>(val data: T?) : ResultResponse<T>()
    data class Error<T>(val errorMessage: String) : ResultResponse<T>()
}