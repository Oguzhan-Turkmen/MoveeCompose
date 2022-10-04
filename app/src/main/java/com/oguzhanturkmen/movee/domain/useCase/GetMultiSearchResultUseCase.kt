package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult
import com.oguzhanturkmen.movee.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMultiSearchResultUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<MultiSearchResult>>> = flow {
        try {
            emit(Resource.Loading())
            val popularMovies = repository.getMultiSearchResult(query)
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