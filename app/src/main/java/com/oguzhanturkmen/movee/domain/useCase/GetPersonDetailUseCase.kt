package com.oguzhanturkmen.movee.domain.useCase

import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.person.Person
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(personId: Int): Flow<Resource<Person>> = flow {
        try {
            emit(Resource.Loading())
            val personDetail = repository.getPersonDetail(personId)
            emit(Resource.Success(personDetail))
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