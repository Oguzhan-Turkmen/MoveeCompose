package com.oguzhanturkmen.movee.data.remote.dto.moviedto

import com.google.gson.annotations.SerializedName

data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
