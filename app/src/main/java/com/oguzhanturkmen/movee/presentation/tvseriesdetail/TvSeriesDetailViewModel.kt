package com.oguzhanturkmen.movee.presentation.tvseriesdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetTvSeriesCreditsUseCase
import com.oguzhanturkmen.movee.domain.useCase.GetTvSeriesDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val getTvSeriesDetailUseCase: GetTvSeriesDetailUseCase,
    private val getTvSeriesCreditsUseCase: GetTvSeriesCreditsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(TvSeriesDetailState())
    val state: State<TvSeriesDetailState> = _state

    private val _tvseriescreditstate = mutableStateOf(TvSeriesCreditState())
    val tvseriescreditstate: State<TvSeriesCreditState> = _tvseriescreditstate

    init {
        savedStateHandle.get<Int>(Constants.PARAM_TVSERIES_ID)?.let { tvSeriesId ->
            getTvSeriesDetail(tvSeriesId)
        }
        savedStateHandle.get<Int>(Constants.PARAM_TVSERIES_ID)?.let { tvSeriesId ->
            getTvSeriesCredit(tvSeriesId)
        }
    }

    private fun getTvSeriesCredit(tvSeriesId: Int) {
        getTvSeriesCreditsUseCase(tvSeriesId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _tvseriescreditstate.value =
                        TvSeriesCreditState(tvSeriesCredit = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _tvseriescreditstate.value = TvSeriesCreditState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _tvseriescreditstate.value = TvSeriesCreditState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTvSeriesDetail(tvSeriesId: Int) {
        getTvSeriesDetailUseCase(tvSeriesId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TvSeriesDetailState(tvSeries = result.data)
                }
                is Resource.Error -> {
                    _state.value = TvSeriesDetailState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TvSeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}
