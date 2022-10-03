package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.mapper.TvSeriesCreditsDtoMapper
import com.oguzhanturkmen.movee.data.mapper.TvSeriesDetailDtoMapper
import com.oguzhanturkmen.movee.data.mapper.TvSeriesDtoMapper
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.domain.model.credits.TvSeriesCast
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.domain.model.seriesdetail.TvSeriesDetail
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import javax.inject.Inject

class TvSeriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tvSeriesMapper: TvSeriesDtoMapper,
    private val tvSeriesDetailDtoMapper: TvSeriesDetailDtoMapper,
    private val tvSeriesCreditsDtoMapper: TvSeriesCreditsDtoMapper
) : TvSeriesRepository {
    override suspend fun getPopularTvSeries(): List<TvSeries> {
        return tvSeriesMapper.toDomainList(apiService.getPopularTvSerials().results)
    }

    override suspend fun getTopRatedTvSeries(): List<TvSeries> {
        return tvSeriesMapper.toDomainList(apiService.getTopRatedTvSerials().results)
    }

    override suspend fun getTvSeriesDetail(tvSeriesId: Int): TvSeriesDetail {
        return tvSeriesDetailDtoMapper.mapToDomainModel(apiService.getTvSeriesDetail(tvSeriesId))
    }

    override suspend fun getTvSeriesCredits(tvId: Int): List<TvSeriesCast> {
        return tvSeriesCreditsDtoMapper.toDomainList(apiService.getTvSeriesCredits(tvId).cast)
    }

}