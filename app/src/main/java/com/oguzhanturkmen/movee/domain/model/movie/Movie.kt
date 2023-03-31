package com.oguzhanturkmen.movee.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
)
