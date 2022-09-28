package com.oguzhanturkmen.movee.presentation.tvseriesdetail

import com.oguzhanturkmen.movee.domain.model.seriesdetail.TvSeriesDetail

data class TvSeriesDetailState(
    val isLoading: Boolean = false,
    val tvSeries: TvSeriesDetail? = null,
    val error: String = ""
)