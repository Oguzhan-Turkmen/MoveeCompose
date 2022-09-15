package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.data.remote.dto.MoviesDto
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):MovieRepository{
    override suspend fun getPopularMovie(): List<MoviesDto> {
        return apiService.getPopularMovies()
    }

}