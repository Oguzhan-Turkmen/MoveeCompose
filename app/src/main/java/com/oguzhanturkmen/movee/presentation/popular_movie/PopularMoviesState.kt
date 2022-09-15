package com.oguzhanturkmen.movee.presentation.popular_movie

import com.oguzhanturkmen.movee.domain.model.Movies

data class PopularMoviesState(
    val isLoading: Boolean = false,
    val popularMovies: List<Movies> = emptyList(),
    val error: String = ""
)

