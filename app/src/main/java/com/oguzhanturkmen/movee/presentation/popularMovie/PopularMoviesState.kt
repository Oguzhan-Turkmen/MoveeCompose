package com.oguzhanturkmen.movee.presentation.popularMovie

import com.oguzhanturkmen.movee.domain.model.movie.Movie

data class PopularMoviesState(
    val isLoading: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val error: String = ""
)

