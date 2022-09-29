package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.creditsdto.CastDto
import com.oguzhanturkmen.movee.domain.model.credits.Cast
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class CreditsDtoMapper @Inject constructor() : DomainMapper<CastDto, Cast> {
    override fun mapToDomainModel(model: CastDto): Cast {
        return Cast(
            id = model.id,
            name = model.name,
            profilePath = model.profilePath,
            creditId = model.creditId
        )
    }

    fun toDomainList(initial: List<CastDto>): List<Cast> = initial.map(this::mapToDomainModel)
}