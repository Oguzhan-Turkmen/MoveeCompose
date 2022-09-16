package com.oguzhanturkmen.movee.presentation.popularMovie

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PopularMoviesState())
    val state: State<PopularMoviesState> = _state

    init {
        getPopularMovies()
    }
    private fun getPopularMovies() {
        Log.d("viewmodel", "burada")
        getPopularMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PopularMoviesState(popularMovies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PopularMoviesState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PopularMoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}