package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.repository.MoveeDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class deleteMovieUseCase @Inject constructor(
    private val repository: MoveeDbRepository

) {
    operator fun invoke(movie: Movie): Flow<Unit> = flow {
        val deleteMovie = repository.deleteMovie(movie)
        emit(deleteMovie)
    }
}