package com.oguzhanturkmen.movee.data.repository

import com.oguzhanturkmen.movee.data.mapper.MultiSearchResultDtoMapper
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult
import com.oguzhanturkmen.movee.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val multiSearchResultDtoMapper: MultiSearchResultDtoMapper
) : SearchRepository {
    override suspend fun getMultiSearchResult(query: String): List<MultiSearchResult> {
        return multiSearchResultDtoMapper.toDomainList(apiService.multiSearch(query).results)
    }
}