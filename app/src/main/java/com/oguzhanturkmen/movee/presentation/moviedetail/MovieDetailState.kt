package com.oguzhanturkmen.movee.presentation.moviedetail

import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)
