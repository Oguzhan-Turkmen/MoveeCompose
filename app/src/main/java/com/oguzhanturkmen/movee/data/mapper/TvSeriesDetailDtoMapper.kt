package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.tvseriesdetaildto.TvSeriesDetailDto
import com.oguzhanturkmen.movee.domain.model.seriesdetail.TvSeriesDetail
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class TvSeriesDetailDtoMapper @Inject constructor() :
    DomainMapper<TvSeriesDetailDto, TvSeriesDetail> {
    override fun mapToDomainModel(model: TvSeriesDetailDto): TvSeriesDetail {
        return TvSeriesDetail(
            id = model.id,
            backdropPath = model.backdropPath,
            posterPath = model.posterPath,
            name = model.name,
            numberOfEpisodes = model.numberOfEpisodes,
            numberOfSeasons = model.numberOfSeasons,
            overview = model.overview,
            voteAverage = model.voteAverage,
            firstAirDate = model.firstAirDate
        )
    }
}