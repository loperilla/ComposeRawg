package com.loperilla.rawg.domain.usecase

import com.loperilla.rawg.data.repository.GenreRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend operator fun invoke() = repository.getAllGenre()
}