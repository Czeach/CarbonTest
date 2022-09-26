package com.czech.core.network

import com.czech.core.response.MovieDetails
import com.czech.core.response.MovieList
import com.czech.core.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String
    ): Response<MovieList>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetails>
}