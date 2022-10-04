package com.oguzhanturkmen.movee.presentation.search

import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult

data class SearchState(
    val isLoading: Boolean = false,
    val searchResult: List<MultiSearchResult> = emptyList(),
    val error: String = ""
)