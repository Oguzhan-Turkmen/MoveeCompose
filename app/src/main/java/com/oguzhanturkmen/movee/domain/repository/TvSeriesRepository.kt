package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.series.TvSeries

interface TvSeriesRepository {
    suspend fun getPopularTvSeries(): List<TvSeries>
    suspend fun getTopRatedTvSeries(): List<TvSeries>
}