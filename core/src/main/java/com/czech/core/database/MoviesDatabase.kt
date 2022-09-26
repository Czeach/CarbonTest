package com.czech.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.czech.core.database.entities.MovieListEntity

@Database(entities = [MovieListEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}