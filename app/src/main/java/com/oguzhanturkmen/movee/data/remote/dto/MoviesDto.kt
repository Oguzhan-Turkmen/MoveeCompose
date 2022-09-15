package com.oguzhanturkmen.movee.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.oguzhanturkmen.movee.domain.model.Movies

data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MoviesDto.toMovies(): Movies {
    return Movies(
        page = page,
        results = results,
        totalPages = totalPages,
        totalResults = totalResults
    )
}