package com.oguzhanturkmen.movee.presentation.profile

import com.oguzhanturkmen.movee.domain.model.movie.Movie

data class SavedMovieState(
    val isLoading: Boolean = false,
    val savedMovies: List<Movie> = emptyList(),
    val error: String = ""
)