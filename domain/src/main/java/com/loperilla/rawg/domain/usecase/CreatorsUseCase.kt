package com.loperilla.rawg.domain.usecase

import com.loperilla.rawg.data.repository.CreatorRepository
import javax.inject.Inject

class CreatorsUseCase @Inject constructor(
    private val creatorRepository: CreatorRepository
) {
    suspend fun getCreatorList() = creatorRepository.getAllCreators()
}
