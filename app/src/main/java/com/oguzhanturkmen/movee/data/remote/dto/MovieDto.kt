package com.oguzhanturkmen.movee.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.oguzhanturkmen.movee.domain.model.Movie


data class MovieDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun  MovieDto.toMovies(): Movie{
    return Movie(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteAvarage = voteAverage
    )
}