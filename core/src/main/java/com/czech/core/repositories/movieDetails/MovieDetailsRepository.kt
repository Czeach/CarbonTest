package com.czech.core.repositories.movieDetails

import com.czech.core.response.MovieDetails
import com.czech.core.response.MovieList
import com.czech.core.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetails>>
}