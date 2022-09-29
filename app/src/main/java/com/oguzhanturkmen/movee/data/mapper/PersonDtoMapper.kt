package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.persondto.PersonDto
import com.oguzhanturkmen.movee.domain.model.person.Person
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class PersonDtoMapper @Inject constructor() : DomainMapper<PersonDto, Person> {
    override fun mapToDomainModel(model: PersonDto): Person {
        return Person(
            id = model.id,
            name = model.name,
            biography = model.biography,
            profilePath = model.profilePath
        )
    }
}