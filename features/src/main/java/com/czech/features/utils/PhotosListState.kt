package com.czech.features.utils

import com.czech.core.response.MovieList

sealed class MoviesListState {
    data class Success(val data: List<MovieList.Result>?) : MoviesListState()
    data class Error(val message: String) : MoviesListState()
    object Loading : MoviesListState()
}