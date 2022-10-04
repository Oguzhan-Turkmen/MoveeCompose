package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult

interface SearchRepository {
    suspend fun getMultiSearchResult(query: String): List<MultiSearchResult>
}