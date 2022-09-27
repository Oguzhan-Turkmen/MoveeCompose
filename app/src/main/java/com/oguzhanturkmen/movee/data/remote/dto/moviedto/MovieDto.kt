package com.oguzhanturkmen.movee.data.remote.dto.moviedto

import com.google.gson.annotations.SerializedName


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

/*
val adult: Boolean,
val backdrop_path: String,
val belongs_to_collection: Any,
val budget: Int,
val genres: List<Genre>,
val homepage: String,
val id: Int,
val imdb_id: String,
val original_language: String,
val original_title: String,
val overview: String,
val popularity: Double,
val poster_path: String,
val production_companies: List<ProductionCompany>,
val production_countries: List<ProductionCountry>,
val release_date: String,
val revenue: Int,
val runtime: Int,
val spoken_languages: List<SpokenLanguage>,
val status: String,
val tagline: String,
val title: String,
val video: Boolean,
val vote_average: Double,
val vote_count: Int
*/
