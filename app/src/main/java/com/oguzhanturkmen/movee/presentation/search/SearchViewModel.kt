package com.oguzhanturkmen.movee.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetMultiSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMultiSearchResultUseCase: GetMultiSearchResultUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state
    var searchParam = mutableStateOf("")
    var previousSearch = mutableStateOf("")

    init {
        searchParam.value = ""
    }

    fun getSearchResults() {
        getMultiSearchResultUseCase(query = searchParam.value).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        SearchState(searchResult = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SearchState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}