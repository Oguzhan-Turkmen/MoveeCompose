package com.oguzhanturkmen.movee.presentation.moviedetail

import com.oguzhanturkmen.movee.domain.model.credits.Cast

data class MovieCreditState(
    val isLoading: Boolean = false,
    val movieCredit: List<Cast> = emptyList(),
    val error: String = ""
)