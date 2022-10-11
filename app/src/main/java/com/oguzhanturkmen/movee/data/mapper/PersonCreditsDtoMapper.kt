package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.personcredits.PersonCastsDto
import com.oguzhanturkmen.movee.domain.model.personcredits.PersonCredits
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class PersonCreditsDtoMapper @Inject constructor() : DomainMapper<PersonCastsDto, PersonCredits> {
    override fun mapToDomainModel(model: PersonCastsDto): PersonCredits {
        return PersonCredits(
            id = model.id,
            originalTitle = model.originalTitle,
            posterPath = model.posterPath,
            voteAverage = model.voteAverage,
            releaseDate = model.releaseDate,
            originalName = model.originalName
        )
    }

    fun toDomainList(initial: List<PersonCastsDto>): List<PersonCredits> =
        initial.map(this::mapToDomainModel)
}