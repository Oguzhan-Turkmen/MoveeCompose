package com.oguzhanturkmen.movee.domain.model.series

data class TvSeries(
    val id: Int,
    val name: String,
    val voteAvarage: Double,
    val posterPath: String?,
    val firstAirDate: String
)