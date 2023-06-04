package com.loperilla.rawg.domain.di

import com.loperilla.rawg.data.repository.CreatorRepository
import com.loperilla.rawg.domain.usecase.CreatorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainDependencyInjector {
    @Provides
    fun provideCreatorsUseCase(
        repository: CreatorRepository
    ) = CreatorsUseCase(repository)

}
