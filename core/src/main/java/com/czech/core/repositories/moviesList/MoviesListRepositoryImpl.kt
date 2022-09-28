package com.czech.core.repositories.moviesList

import com.czech.core.BuildConfig
import com.czech.core.database.repositories.MoviesDaoRepository
import com.czech.core.network.ApiService
import com.czech.core.response.MovieList
import com.czech.core.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.czech.core.utils.toEntityList
import com.czech.core.utils.toResultList

class MoviesListRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesDaoRepository: MoviesDaoRepository
) : MoviesListRepository {

    override suspend fun getMoviesFromNetwork(): Flow<DataState<List<MovieList.Result>>> {
        return flow {

            emit(DataState.loading())

            val response = apiService.getMoviesList(BuildConfig.API_KEY)

            val movieList = response.body()?.results

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (movieList.isNullOrEmpty()) {
                            emit(DataState.data(message = "No movies found at the moment"))
                        } else {
                            moviesDaoRepository.insertMovies(movieList.toEntityList())

                            emit(DataState.data(data = moviesDaoRepository.getMovies().toResultList()))
                        }
                    }
                    false -> {
                        emit(DataState.error(message = "Error ${response.errorBody().toString()}"))
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

    override suspend fun getMoviesFromDatabase(): Flow<DataState<List<MovieList.Result>>> {
        return flow {

            emit(DataState.loading())

            val result = moviesDaoRepository.getMovies().toResultList()

            try {
                if (result.isNullOrEmpty()) {
                    emit(DataState.error(message = "You don't have any saved movies. Connect to the internet and refresh to get new results"))
                } else {
                    emit(DataState.data(data = result))
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