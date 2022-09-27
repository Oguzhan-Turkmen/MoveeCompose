package com.oguzhanturkmen.movee.presentation.tvseries.populartvserial

import com.oguzhanturkmen.movee.domain.model.series.TvSeries

data class PopulatTvSerialState(
    val isLoading: Boolean = false,
    val popularTvSerials: List<TvSeries> = emptyList(),
    val error: String = ""
)