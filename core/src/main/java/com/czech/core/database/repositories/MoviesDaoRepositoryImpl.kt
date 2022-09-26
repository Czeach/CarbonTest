package com.czech.core.database.repositories

import com.czech.core.database.MoviesDao
import com.czech.core.database.entities.MovieListEntity
import javax.inject.Inject

class MoviesDaoRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : MoviesDaoRepository {

    override suspend fun insertMovies(movies: List<MovieListEntity>) {
        moviesDao.insertMovies(movies)
    }

    override suspend fun getMovies(): List<MovieListEntity> {
        return moviesDao.getMovies()
    }
}