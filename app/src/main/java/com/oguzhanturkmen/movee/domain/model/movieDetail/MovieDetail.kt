package com.oguzhanturkmen.movee.domain.model.movieDetail

data class MovieDetail(
    val id: Int,
    val backdropPath: String,
    val overview: String,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    )