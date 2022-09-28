package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.tvseriesdto.TvSeriesDto
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class TvSeriesDtoMapper @Inject constructor() : DomainMapper<TvSeriesDto, TvSeries> {
    override fun mapToDomainModel(model: TvSeriesDto): TvSeries {
        return TvSeries(
            id = model.id,
            name = model.name,
            voteAvarage = model.voteAverage,
            posterPath = model.posterPath,
            firstAirDate = model.firstAirDate
        )
    }

    fun toDomainList(initial: List<TvSeriesDto>): List<TvSeries> =
        initial.map(this::mapToDomainModel)
}