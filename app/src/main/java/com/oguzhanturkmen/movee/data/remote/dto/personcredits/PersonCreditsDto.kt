package com.oguzhanturkmen.movee.data.remote.dto.personcredits

data class PersonCreditsDto(
    val cast: List<PersonCastsDto>,
    val crew: List<Crew>,
    val id: Int
)