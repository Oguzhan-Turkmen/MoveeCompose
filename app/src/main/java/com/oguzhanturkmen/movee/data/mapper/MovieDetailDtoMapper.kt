package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.movieDetailDto.MovieDetailDto
import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject

class MovieDetailDtoMapper @Inject constructor() : DomainMapper<MovieDetailDto, MovieDetail> {
    override fun mapToDomainModel(model: MovieDetailDto): MovieDetail {
        return MovieDetail(
            id = model.id,
            backdropPath = model.backdropPath,
            overview = model.overview,
            originalTitle = model.originalTitle,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            runtime = model.runtime,
            voteAverage = model.voteAverage
        )
    }
}