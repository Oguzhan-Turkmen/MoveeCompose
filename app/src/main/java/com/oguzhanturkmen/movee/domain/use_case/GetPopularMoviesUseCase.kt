package com.oguzhanturkmen.movee.domain.use_case

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.data.remote.dto.toMovies
import com.oguzhanturkmen.movee.domain.model.Movies
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movies>>> = flow {
        try {
            emit(Resource.Loading<List<Movies>>())
            val popularMovies = repository.getPopularMovie().map { it.toMovies() }
            emit(Resource.Success<List<Movies>>(popularMovies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Movies>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Movies>>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}