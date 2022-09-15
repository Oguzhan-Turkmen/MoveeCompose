package com.oguzhanturkmen.movee.presentation.popular_movie

import com.oguzhanturkmen.movee.domain.model.Movie

data class PopularMoviesState(
    val isLoading: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val error: String = ""
)

