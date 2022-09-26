package com.czech.features.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.core.network.NetworkConnection
import com.czech.core.repositories.moviesList.MoviesListRepository
import com.czech.features.utils.MoviesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesListRepository: MoviesListRepository,
    private val networkConnection: NetworkConnection
) : ViewModel() {

    val isNetworkConnected = networkConnection
    val moviesListState = MutableStateFlow<MoviesListState?>(null)

    fun getMoviesFromNetwork() {
        viewModelScope.launch {
            moviesListRepository.getMoviesFromNetwork().collect { it ->
                when {
                    it.isLoading -> {
                        moviesListState.value = MoviesListState.Loading
                    }
                    it.data == null -> {
                        moviesListState.value = MoviesListState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { movies ->
                            moviesListState.value = MoviesListState.Success(data = movies)
                        }
                    }
                }
            }
        }
    }

    fun getMoviesFromDB() {
        viewModelScope.launch {
            moviesListRepository.getMoviesFromDatabase().collect {
                when {
                    it.isLoading -> {
                        moviesListState.value = MoviesListState.Loading
                    }
                    it.data == null -> {
                        moviesListState.value = MoviesListState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { movies ->
                            moviesListState.value = MoviesListState.Success(data = movies)
                        }
                    }
                }
            }
        }
    }
}