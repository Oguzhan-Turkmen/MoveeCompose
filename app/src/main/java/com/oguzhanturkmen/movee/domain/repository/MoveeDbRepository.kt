package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.movie.Movie

interface MoveeDbRepository {
    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    suspend fun getAllMovies(): List<Movie>
}