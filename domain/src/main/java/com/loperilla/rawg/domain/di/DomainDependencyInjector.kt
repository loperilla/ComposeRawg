package com.loperilla.rawg.domain.di

import com.loperilla.rawg.data.repository.GenreRepository
import com.loperilla.rawg.domain.usecase.GenreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainDependencyInjector {
    @Provides
    fun provideGenreList(
        repository: GenreRepository
    ) = GenreUseCase(repository)

//    @Provides
//    fun provideCreatorsUseCase(
//        repository: CreatorRepository
//    ) = CreatorsUseCase(repository)

}
