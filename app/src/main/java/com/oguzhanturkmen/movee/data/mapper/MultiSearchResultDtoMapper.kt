package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.search.MultiSearchResultDto
import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class MultiSearchResultDtoMapper @Inject constructor() :
    DomainMapper<MultiSearchResultDto, MultiSearchResult> {
    override fun mapToDomainModel(model: MultiSearchResultDto): MultiSearchResult {
        return MultiSearchResult(
            backdrop_path = model.backdropPath,
            id = model.id,
            mediaType = model.mediaType,
            posterPath = model.posterPath,
            overview = model.overview,
            originalTitle = model.originalTitle,
            name = model.name,
            profilePath = model.profilePath
        )
    }

    fun toDomainList(initial: List<MultiSearchResultDto>): List<MultiSearchResult> =
        initial.map(this::mapToDomainModel)
}