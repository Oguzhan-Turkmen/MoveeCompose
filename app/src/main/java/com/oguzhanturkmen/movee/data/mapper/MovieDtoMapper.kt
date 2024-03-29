package com.oguzhanturkmen.movee.data.mapper

import com.oguzhanturkmen.movee.data.remote.dto.moviedto.MovieDto
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.util.DomainMapper
import javax.inject.Inject


class MovieDtoMapper @Inject constructor() : DomainMapper<MovieDto, Movie> {
    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            id = model.id,
            originalTitle = model.originalTitle,
            posterPath = model.posterPath,
            voteAverage = model.voteAverage,
            releaseDate = model.releaseDate,
            overview = model.overview,
        )
    }
    fun toDomainList(initial: List<MovieDto>): List<Movie> = initial.map(this::mapToDomainModel)
}