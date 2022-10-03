package com.oguzhanturkmen.movee.presentation.tvseriesdetail

import com.oguzhanturkmen.movee.domain.model.credits.TvSeriesCast

data class TvSeriesCreditState(
    val isLoading: Boolean = false,
    val tvSeriesCredit: List<TvSeriesCast> = emptyList(),
    val error: String = ""
)