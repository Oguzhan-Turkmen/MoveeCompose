package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.local.MoveeDao
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.repository.MoveeDbRepository
import javax.inject.Inject

class MoveeDbRepositoryImpl @Inject constructor(
    private val moveeDao: MoveeDao
) : MoveeDbRepository {
    override suspend fun saveMovie(movie: Movie) {
        moveeDao.saveMovie(movie = movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        moveeDao.deleteMovie(movie = movie)
    }

    override suspend fun getAllMovies(): List<Movie> {
        return moveeDao.getAllMovies()
    }
}