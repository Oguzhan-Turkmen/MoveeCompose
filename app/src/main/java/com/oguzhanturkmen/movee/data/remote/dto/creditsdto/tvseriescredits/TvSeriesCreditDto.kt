package com.oguzhanturkmen.movee.data.remote.dto.creditsdto.tvseriescredits

data class TvSeriesCreditDto(
    val cast: List<TvSeriesCastDto>,
    val crew: List<Crew>,
    val id: Int
)