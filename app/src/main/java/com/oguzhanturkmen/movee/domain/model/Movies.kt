package com.oguzhanturkmen.movee.domain.model

import com.google.gson.annotations.SerializedName
import com.oguzhanturkmen.movee.data.remote.dto.MovieDto

data class Movies(
    val page: Int,
    val results: List<MovieDto>,
    val totalPages: Int,
    val totalResults: Int
)