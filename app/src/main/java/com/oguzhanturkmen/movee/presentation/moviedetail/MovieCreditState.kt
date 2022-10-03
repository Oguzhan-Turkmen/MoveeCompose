package com.oguzhanturkmen.movee.presentation.moviedetail

import com.oguzhanturkmen.movee.domain.model.credits.MovieCast

data class MovieCreditState(
    val isLoading: Boolean = false,
    val movieCredit: List<MovieCast> = emptyList(),
    val error: String = ""
)