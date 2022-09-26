package com.czech.core.repositories.movieDetails

import com.czech.core.BuildConfig
import com.czech.core.network.ApiService
import com.czech.core.response.MovieDetails
import com.czech.core.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetails>> {
        return flow {

            emit(DataState.loading())

            val response = apiService.getMovieDetails(movieId = movieId, apiKey = BuildConfig.API_KEY)

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (response.body() == null) {
                            emit(DataState.data(message = "Movie not available at the moment"))
                        } else {
                            emit(DataState.data(data = response.body()))
                        }
                    }
                    false -> {
                        emit(DataState.error(message = "Error ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }

        }.flowOn(Dispatchers.IO)
    }
}