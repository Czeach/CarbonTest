package com.czech.features.utils

import com.czech.core.response.MovieDetails

sealed class MovieDetailState {
    data class Success(val data: MovieDetails?) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
    object Loading : MovieDetailState()
}