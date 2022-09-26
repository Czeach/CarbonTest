package com.czech.carbontest.viewModels

import com.czech.core.network.NetworkConnection
import com.czech.core.repositories.moviesList.MoviesListRepository
import com.czech.core.response.MovieList
import com.czech.core.utils.DataState
import com.czech.features.moviesList.MoviesListViewModel
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
class MoviesListViewModelTest {

    @Mock
    private lateinit var moviesListRepository: MoviesListRepository

    @Mock
    private lateinit var networkConnection: NetworkConnection

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
    fun testMovieListViewModel() = testCoroutineDispatcher.runBlockingTest {

        val networkResponse = listOf(MovieList.Result (
            id = 0,
            backdropPath = "",
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity= 0.0,
            posterPath = "",
            releaseDate = "",
            title = "",
            voteAverage = 0.0,
            voteCount = 0)
        )

        val moviesListViewModel = MoviesListViewModel(moviesListRepository, networkConnection)

        val response = DataState.data(networkResponse.toString(), networkResponse)

        val channel = Channel<DataState<List<MovieList.Result>>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(moviesListRepository.getMoviesFromNetwork()).thenReturn(flow)

        launch {
            channel.send(response)
        }

        moviesListViewModel.getMoviesFromNetwork()

        Assert.assertEquals(true, moviesListViewModel.moviesListState.value == MoviesListState.Success(networkResponse))
        Assert.assertEquals(false, moviesListViewModel.moviesListState.value == MoviesListState.Loading)
        Assert.assertEquals(false, moviesListViewModel.moviesListState.value == MoviesListState.Error(""))
    }
}