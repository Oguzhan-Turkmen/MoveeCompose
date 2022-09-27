package com.oguzhanturkmen.movee.domain.model.movie

data class Movie(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val voteAvarage: Double,
    val releaseDate: String,

    )
