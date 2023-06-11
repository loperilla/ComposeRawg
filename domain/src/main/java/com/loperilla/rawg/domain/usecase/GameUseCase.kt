package com.loperilla.rawg.domain.usecase

import com.loperilla.rawg.data.repository.GameRepository
import javax.inject.Inject

class GameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(searchInputQuery: String) = repository.getGames(searchInputQuery)
}