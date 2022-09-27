package com.oguzhanturkmen.movee.presentation.topratedtvseries

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetTopRatedTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopRatedTvSeriesViewModel @Inject constructor(
    private val getTopRatedTvSeriesUseCase: GetTopRatedTvSeriesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TopRatedTvSeriesState())
    val state: State<TopRatedTvSeriesState> = _state

    init {
        getTopRatedTvSeries()
    }

    private fun getTopRatedTvSeries() {
        getTopRatedTvSeriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        TopRatedTvSeriesState(topRatedTvSerials = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TopRatedTvSeriesState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TopRatedTvSeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}