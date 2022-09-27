package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularTvSerialsUseCase @Inject constructor(
    private val repository: TvSeriesRepository
) {
    operator fun invoke(): Flow<Resource<List<TvSeries>>> = flow {
        try {
            emit(Resource.Loading())
            val popularMovies = repository.getPopularTvSeries()
            emit(Resource.Success(popularMovies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}