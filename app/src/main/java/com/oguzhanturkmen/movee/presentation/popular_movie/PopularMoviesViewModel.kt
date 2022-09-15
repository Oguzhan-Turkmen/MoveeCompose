package com.oguzhanturkmen.movee.presentation.popular_movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.use_case.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
        }
    }
}