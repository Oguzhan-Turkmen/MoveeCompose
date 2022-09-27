package com.oguzhanturkmen.movee.domain.model.movie

import com.oguzhanturkmen.movee.data.remote.dto.moviedto.MovieDto

data class Movies(
    val page: Int,
    val results: List<MovieDto>,
    val totalPages: Int,
    val totalResults: Int
)