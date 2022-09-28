package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.seriesdetail.TvSeriesDetail
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvSeriesDetailUseCase @Inject constructor(
    private val repository: TvSeriesRepository
) {
    operator fun invoke(tvSeriesId: Int): Flow<Resource<TvSeriesDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getTvSeriesDetail(tvSeriesId)
            emit(Resource.Success(movieDetail))
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