package com.czech.carbontest.viewModels

import com.czech.core.network.NetworkConnection
import com.czech.core.repositories.movieDetails.MovieDetailsRepository
import com.czech.core.repositories.moviesList.MoviesListRepository
import com.czech.core.response.MovieDetails
import com.czech.core.response.MovieList
import com.czech.core.utils.DataState
import com.czech.features.movieDetails.MovieDetailsViewModel
import com.czech.features.moviesList.MoviesListViewModel
import com.czech.features.utils.MovieDetailState
import com.czech.features.utils.MoviesListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    @Mock
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testMovieDetailsViewModel() = testCoroutineDispatcher.runBlockingTest {

        val networkResponse = MovieDetails(
            id = 0,
            backdropPath = "",
            originalLanguage = "",
            originalTitle =  "",
            overview = "",
            posterPath = "",
            releaseDate = "",
            title = "",
            voteAverage = 0.0,
            voteCount = 0

        )

        val movieDetailsViewModel = MovieDetailsViewModel(movieDetailsRepository)

        val movieId = 0

        val response = DataState.data(networkResponse.toString(), networkResponse)

        val channel = Channel<DataState<MovieDetails>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(movieDetailsRepository.getMovieDetails(0)).thenReturn(flow)

        launch {
            channel.send(response)
        }

        movieDetailsViewModel.getMovieDetails(movieId)

        Assert.assertEquals(true, movieDetailsViewModel.moviesDetailState.value == MovieDetailState.Success(networkResponse))
        Assert.assertEquals(false, movieDetailsViewModel.moviesDetailState.value == MovieDetailState.Loading)
        Assert.assertEquals(false, movieDetailsViewModel.moviesDetailState.value == MovieDetailState.Error(""))
    }
}