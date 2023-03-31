package com.oguzhanturkmen.movee.presentation.moviedetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.useCase.GetMovieCreditsUseCase
import com.oguzhanturkmen.movee.domain.useCase.GetMovieDetailUseCase
import com.oguzhanturkmen.movee.domain.useCase.saveMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val saveMovieUseCase: saveMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    private val _moviecreditstate = mutableStateOf(MovieCreditState())
    val moviecreditstate: State<MovieCreditState> = _moviecreditstate

    init {
        savedStateHandle.get<Int>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getMovieDetail(movieId)
        }
        savedStateHandle.get<Int>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getMovieCredit(movieId)
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            saveMovieUseCase(movie)
                .flowOn(Dispatchers.IO)
                .collect {
                }
        }
    }

    private fun getMovieCredit(movieId: Int) {
        getMovieCreditsUseCase(movieId = movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _moviecreditstate.value =
                        MovieCreditState(movieCredit = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _moviecreditstate.value = MovieCreditState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _moviecreditstate.value = MovieCreditState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = result.data)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}