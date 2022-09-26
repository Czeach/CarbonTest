package com.czech.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czech.core.database.entities.MovieListEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieListEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieListEntity>

}