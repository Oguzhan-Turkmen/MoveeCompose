package com.oguzhanturkmen.movee.presentation.tvseries.populartvserial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetPopularTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularTvSeriesViewModel @Inject constructor(
    private val getPopularTvSerialsUseCase: GetPopularTvSeriesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PopularTvSeriesState())
    val state: State<PopularTvSeriesState> = _state

    init {
        getPopularTvSeries()
    }

    private fun getPopularTvSeries() {
        getPopularTvSerialsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        PopularTvSeriesState(popularTvSerials = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PopularTvSeriesState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PopularTvSeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}