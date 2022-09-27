package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.mapper.TvSeriesMapper
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import javax.inject.Inject

class TvSeriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tvSeriesMapper: TvSeriesMapper,
) : TvSeriesRepository {
    override suspend fun getPopularTvSeries(): List<TvSeries> {
        return tvSeriesMapper.toDomainList(apiService.getPopularTvSerials().results)
    }

    override suspend fun getTopRatedTvSeries(): List<TvSeries> {
        return tvSeriesMapper.toDomainList(apiService.getTopRatedTvSerials().results)
    }

}