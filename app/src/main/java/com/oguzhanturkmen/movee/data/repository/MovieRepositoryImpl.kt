package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.mapper.MovieDtoMapper
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.domain.model.Movie
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDtoMapper: MovieDtoMapper
):MovieRepository {
    override suspend fun getPopularMovie(): List<Movie> {
        return movieDtoMapper.toDomainList(apiService.getPopularMovies().results)
    }

    override suspend fun getNowPlayingMovie(): List<Movie> {
        return movieDtoMapper.toDomainList(apiService.getNowPlayingMovies().results)
    }
}