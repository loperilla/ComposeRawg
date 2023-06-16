package com.loperilla.rawg.domain.usecase

import com.loperilla.rawg.data.repository.QueryRepository
import javax.inject.Inject

class QueryUseCase @Inject constructor(
    private val queryRepository: QueryRepository
) {
    operator fun invoke() = queryRepository.collectAllPreviousQuery()

    suspend fun insertNewQuery(query: String) = queryRepository.insertNewQuery(query)
}
