package com.czech.features.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.core.repositories.movieDetails.MovieDetailsRepository
import com.czech.features.utils.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
): ViewModel() {

    val moviesDetailState = MutableStateFlow<MovieDetailState?>(null)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsRepository.getMovieDetails(movieId).collect { it ->
                when {
                    it.isLoading -> {
                        moviesDetailState.value = MovieDetailState.Loading
                    }
                    it.data == null -> {
                        moviesDetailState.value = MovieDetailState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { movie ->
                            moviesDetailState.value = MovieDetailState.Success(data = movie)
                        }
                    }
                }
            }
        }
    }
}