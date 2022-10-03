package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.creditsdto.tvseriescredits.TvSeriesCastDto
import com.oguzhanturkmen.movee.domain.model.credits.TvSeriesCast
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class TvSeriesCreditsDtoMapper @Inject constructor() : DomainMapper<TvSeriesCastDto, TvSeriesCast> {
    override fun mapToDomainModel(model: TvSeriesCastDto): TvSeriesCast {
        return TvSeriesCast(
            id = model.id,
            name = model.name,
            profilePath = model.profilePath
        )
    }

    fun toDomainList(initial: List<TvSeriesCastDto>): List<TvSeriesCast> =
        initial.map(this::mapToDomainModel)
}