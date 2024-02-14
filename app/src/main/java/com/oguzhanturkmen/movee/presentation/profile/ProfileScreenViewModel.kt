package com.oguzhanturkmen.movee.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.useCase.DeleteMovieUseCase
import com.oguzhanturkmen.movee.domain.useCase.GetAllMoviesFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getAllMoviesFromDbUseCase: GetAllMoviesFromDbUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SavedMovieState())
    val state: State<SavedMovieState> = _state

    init {
        getSavedMovies()
    }

    private fun getSavedMovies() {
        viewModelScope.launch {
            val data = getAllMoviesFromDbUseCase.invoke().collectLatest {
                _state.value = SavedMovieState(savedMovies = it)
            }
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            deleteMovieUseCase(movie)
                .flowOn(Dispatchers.IO)
                .collect {

                }
        }
    }
}