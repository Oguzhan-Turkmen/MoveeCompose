package com.oguzhanturkmen.movee.data.remote.dto.creditsdto.moviecredits

data class CreditsDto(
    val cast: List<CastDto>,
    val crew: List<Crew>,
    val id: Int
)