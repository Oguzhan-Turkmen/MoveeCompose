package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.credits.TvSeriesCast
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.domain.model.seriesdetail.TvSeriesDetail

interface TvSeriesRepository {
    suspend fun getPopularTvSeries(): List<TvSeries>
    suspend fun getTopRatedTvSeries(): List<TvSeries>
    suspend fun getTvSeriesDetail(tvSeriesId: Int): TvSeriesDetail
    suspend fun getTvSeriesCredits(tvId: Int): List<TvSeriesCast>
}