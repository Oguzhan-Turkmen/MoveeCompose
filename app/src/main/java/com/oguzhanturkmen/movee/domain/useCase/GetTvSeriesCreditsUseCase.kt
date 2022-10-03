package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.credits.TvSeriesCast
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvSeriesCreditsUseCase @Inject constructor(
    private val repository: TvSeriesRepository
) {
    operator fun invoke(tvId: Int): Flow<Resource<List<TvSeriesCast>>> = flow {
        try {
            emit(Resource.Loading())
            val tvSeriesCredits = repository.getTvSeriesCredits(tvId)
            emit(Resource.Success(tvSeriesCredits))
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