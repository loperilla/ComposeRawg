package com.loperilla.rawg.data.di

import com.loperilla.rawg.data.repository.CreatorRepository
import com.loperilla.rawg.datasource.network.api.CreatorsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDependencyInjector {
    @Provides
    @Singleton
    fun provideCreatorRepository(
        creatorsApi: CreatorsApi
    ) = CreatorRepository(creatorsApi)
}
