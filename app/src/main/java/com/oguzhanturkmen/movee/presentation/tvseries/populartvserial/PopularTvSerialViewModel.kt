package com.oguzhanturkmen.movee.presentation.tvseries.populartvserial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetPopularTvSerialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularTvSerialViewModel @Inject constructor(
    private val getPopularTvSerialsUseCase: GetPopularTvSerialsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PopulatTvSerialState())
    val state: State<PopulatTvSerialState> = _state

    init {
        getPopularTvSerials()
    }

    private fun getPopularTvSerials() {
        getPopularTvSerialsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        PopulatTvSerialState(popularTvSerials = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PopulatTvSerialState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PopulatTvSerialState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}