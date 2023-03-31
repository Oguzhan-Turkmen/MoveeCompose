package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.repository.MoveeDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class getAllMoviesFromDbUseCase @Inject constructor(
    private val repository: MoveeDbRepository
) {
    operator fun invoke(): Flow<List<Movie>> = flow {
        val allMovies = repository.getAllMovies()
        emit(allMovies)
    }
}