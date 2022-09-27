package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val nowPlayingMovies = repository.getNowPlayingMovie()
            emit(Resource.Success<List<Movie>>(nowPlayingMovies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Movie>>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )

        }
    }
}