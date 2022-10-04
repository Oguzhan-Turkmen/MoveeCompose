package com.oguzhanturkmen.movee.data.remote.dto.search

data class MultiSearchDto(
    val page: Int,
    val results: List<MultiSearchResultDto>,
    val total_pages: Int,
    val total_results: Int
)