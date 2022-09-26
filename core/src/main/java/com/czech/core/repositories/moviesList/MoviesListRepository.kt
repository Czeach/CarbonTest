package com.czech.core.repositories.moviesList

import com.czech.core.response.MovieList
import com.czech.core.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MoviesListRepository {

    suspend fun getMoviesFromNetwork(): Flow<DataState<List<MovieList.Result>>>

    suspend fun getMoviesFromDatabase(): Flow<DataState<List<MovieList.Result>>>
}