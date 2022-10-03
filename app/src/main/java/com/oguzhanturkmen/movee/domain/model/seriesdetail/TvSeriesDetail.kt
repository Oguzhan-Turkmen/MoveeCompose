package com.oguzhanturkmen.movee.domain.model.seriesdetail

data class TvSeriesDetail(
    val id: Int,
    val backdropPath: String?,
    val posterPath: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val voteAverage: Double,
    val firstAirDate: String,


    )