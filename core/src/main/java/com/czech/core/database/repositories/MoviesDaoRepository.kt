package com.czech.core.database.repositories

import com.czech.core.database.entities.MovieListEntity

interface MoviesDaoRepository {

    suspend fun insertMovies(movies: List<MovieListEntity>)

    suspend fun getMovies(): List<MovieListEntity>
}