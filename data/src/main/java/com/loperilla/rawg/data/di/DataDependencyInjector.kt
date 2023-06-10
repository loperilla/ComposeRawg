package com.loperilla.rawg.data.di

import com.loperilla.rawg.data.repository.GameRepository
import com.loperilla.rawg.data.repository.GenreRepository
import com.loperilla.rawg.datasource.database.dao.GameGenreDao
import com.loperilla.rawg.datasource.network.api.GameApi
import com.loperilla.rawg.datasource.network.api.GameGenreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDependencyInjector {
//    @Provides
//    @Singleton
//    fun provideCreatorRepository(
//        creatorsApi: CreatorsApi
//    ) = CreatorRepository(creatorsApi)

    @Provides
    @Singleton
    fun provideGenreRepository(
        genreApi: GameGenreApi,
        genreDao: GameGenreDao
    ) = GenreRepository(genreApi, genreDao)

    @Provides
    @Singleton
    fun provideGameRepository(
        gameApi: GameApi
    ) = GameRepository(gameApi)
}
