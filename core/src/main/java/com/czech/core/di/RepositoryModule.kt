package com.czech.core.di

import com.czech.core.database.MoviesDao
import com.czech.core.database.repositories.MoviesDaoRepository
import com.czech.core.database.repositories.MoviesDaoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class RepositoryModule {

    @[Provides Singleton]
    fun provideMoviesDaoRepository(
        moviesDao: MoviesDao
    ): MoviesDaoRepository {
        return MoviesDaoRepositoryImpl(
            moviesDao = moviesDao
        )
    }
}