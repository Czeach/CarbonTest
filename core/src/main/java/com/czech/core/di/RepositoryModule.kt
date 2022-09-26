package com.czech.core.di

import com.czech.core.database.MoviesDao
import com.czech.core.database.repositories.MoviesDaoRepository
import com.czech.core.database.repositories.MoviesDaoRepositoryImpl
import com.czech.core.network.ApiService
import com.czech.core.repositories.movieDetails.MovieDetailsRepository
import com.czech.core.repositories.movieDetails.MovieDetailsRepositoryImpl
import com.czech.core.repositories.moviesList.MoviesListRepository
import com.czech.core.repositories.moviesList.MoviesListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RepositoryModule {

    @[Provides Singleton]
    fun provideMoviesDaoRepository(
        moviesDao: MoviesDao
    ): MoviesDaoRepository {
        return MoviesDaoRepositoryImpl(
            moviesDao = moviesDao
        )
    }

    @[Provides Singleton]
    fun provideMoviesListRepository(
        apiService: ApiService,
        moviesDaoRepository: MoviesDaoRepository
    ): MoviesListRepository {
        return MoviesListRepositoryImpl(
            apiService = apiService,
            moviesDaoRepository = moviesDaoRepository
        )
    }

    @[Provides Singleton]
    fun provideMovieDetailsRepository(
        apiService: ApiService,
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(
            apiService = apiService
        )
    }
}