package com.oguzhanturkmen.movee.data.remote.dto.tvseriesdto

data class TvSerialsDto(
    val page: Int,
    val results: List<TvSeriesDto>,
    val total_pages: Int,
    val total_results: Int
)