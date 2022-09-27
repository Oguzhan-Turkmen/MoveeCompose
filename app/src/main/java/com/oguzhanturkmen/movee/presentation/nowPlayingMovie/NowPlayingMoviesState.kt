package com.oguzhanturkmen.movee.presentation.nowPlayingMovie

import com.oguzhanturkmen.movee.domain.model.movie.Movie

data class NowPlayingMoviesState(
    val isLoading: Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val error: String = ""
)