package com.oguzhanturkmen.movee.presentation.topratedtvseries

import com.oguzhanturkmen.movee.domain.model.series.TvSeries

data class TopRatedTvSeriesState(
    val isLoading: Boolean = false,
    val topRatedTvSerials: List<TvSeries> = emptyList(),
    val error: String = ""
)