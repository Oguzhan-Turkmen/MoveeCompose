package com.oguzhanturkmen.movee.domain.model.personcredits

data class PersonCredits(
    val id: Int,
    val originalTitle: String?,
    val posterPath: String?,
    val voteAverage: Double,
    val releaseDate: String?,
    val originalName: String?,
)