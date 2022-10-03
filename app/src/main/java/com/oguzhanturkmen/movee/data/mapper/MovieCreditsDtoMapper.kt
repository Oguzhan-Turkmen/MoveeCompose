package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.creditsdto.moviecredits.CastDto
import com.oguzhanturkmen.movee.domain.model.credits.MovieCast
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class MovieCreditsDtoMapper @Inject constructor() : DomainMapper<CastDto, MovieCast> {
    override fun mapToDomainModel(model: CastDto): MovieCast {
        return MovieCast(
            id = model.id,
            name = model.name,
            profilePath = model.profilePath,
            creditId = model.creditId
        )
    }

    fun toDomainList(initial: List<CastDto>): List<MovieCast> = initial.map(this::mapToDomainModel)
}