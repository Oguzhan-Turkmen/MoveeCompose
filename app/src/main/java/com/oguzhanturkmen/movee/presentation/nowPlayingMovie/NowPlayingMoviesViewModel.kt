package com.oguzhanturkmen.movee.presentation.nowPlayingMovie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NowPlayingMoviesState())
    val state: State<NowPlayingMoviesState> = _state

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        getNowPlayingMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        NowPlayingMoviesState(nowPlayingMovies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NowPlayingMoviesState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = NowPlayingMoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
