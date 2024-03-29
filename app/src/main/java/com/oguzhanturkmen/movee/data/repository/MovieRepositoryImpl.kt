package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.mapper.*
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.domain.model.credits.MovieCast
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail
import com.oguzhanturkmen.movee.domain.model.person.Person
import com.oguzhanturkmen.movee.domain.model.personcredits.PersonCredits
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDtoMapper: MovieDtoMapper,
    private val movieDetailDtoMapper: MovieDetailDtoMapper,
    private val creditsDtoMapper: MovieCreditsDtoMapper,
    private val personDtoMapper: PersonDtoMapper,
    private val personCreditsDtoMapper: PersonCreditsDtoMapper
):MovieRepository {
    override suspend fun getPopularMovie(): List<Movie> {
        return movieDtoMapper.toDomainList(apiService.getPopularMovies().results)
    }

    override suspend fun getNowPlayingMovie(): List<Movie> {
        return movieDtoMapper.toDomainList(apiService.getNowPlayingMovies().results)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return movieDetailDtoMapper.mapToDomainModel(apiService.getMovieDetail(movieId))
    }

    override suspend fun getMovieCredits(movieId: Int): List<MovieCast> {
        return creditsDtoMapper.toDomainList(apiService.getMovieCredits(movieId).cast)
    }

    override suspend fun getPersonDetail(personId: Int): Person {
        return personDtoMapper.mapToDomainModel(apiService.getPersonDetail(personId))
    }

    override suspend fun getPersonCredits(personId: Int): List<PersonCredits> {
        return personCreditsDtoMapper.toDomainList(apiService.getPersonCredits(personId).cast)
    }
}
