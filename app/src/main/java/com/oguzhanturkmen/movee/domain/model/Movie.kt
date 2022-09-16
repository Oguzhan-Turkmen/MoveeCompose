package com.oguzhanturkmen.movee.domain.model

data class Movie(
    val id: Int,
    val originalTitle: String,
    val posterPath: String,
    val voteAvarage: Double,
    val releaseDate: String
)
