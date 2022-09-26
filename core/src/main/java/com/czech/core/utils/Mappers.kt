package com.czech.core.utils

import com.czech.core.response.MovieList
import com.czech.core.database.entities.MovieListEntity

fun MovieListEntity.toResult(): MovieList.Result {
    return MovieList.Result(
        id = id,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun List<MovieListEntity>.toResultList(): List<MovieList.Result> {
    return map { it.toResult() }
}

fun MovieList.Result.toEntity(): MovieListEntity {
    return MovieListEntity(
        id = id,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun List<MovieList.Result>.toEntityList(): List<MovieListEntity> {
    return map { it.toEntity() }
}