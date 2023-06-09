package com.loperilla.rawg.datasource.database.di

import android.content.Context
import androidx.room.Room
import com.loperilla.rawg.datasource.database.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDI {
    @Provides
    @Singleton
    fun provideGameDatabase(
        context: Context
    ): GameDatabase = Room
        .databaseBuilder(
            context,
            GameDatabase::class.java,
            GameDatabase::class.java.simpleName
        ).build()

    @Provides
    @Singleton
    fun provideGameGenreDao(
        database: GameDatabase
    ) = database.genreDao()

}