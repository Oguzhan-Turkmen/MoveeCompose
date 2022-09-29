package com.oguzhanturkmen.movee.presentation.personDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.domain.useCase.GetPersonCreditsUseCase
import com.oguzhanturkmen.movee.domain.useCase.GetPersonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val getPersonDetailUseCase: GetPersonDetailUseCase,
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PersonDetailState())
    val state: State<PersonDetailState> = _state

    private val _personcreditstate = mutableStateOf(PersonDetailCreditsState())
    val personcreditstate: State<PersonDetailCreditsState> = _personcreditstate

    init {
        savedStateHandle.get<Int>(Constants.PARAM_PERSON_ID)?.let { personId ->
            getPersonDetail(personId)
        }
        savedStateHandle.get<Int>(Constants.PARAM_PERSON_ID)?.let { personId ->
            getPersonCredits(personId)
        }
    }

    private fun getPersonCredits(personId: Int) {
        getPersonCreditsUseCase(personId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _personcreditstate.value =
                        PersonDetailCreditsState(personCredit = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _personcreditstate.value = PersonDetailCreditsState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _personcreditstate.value = PersonDetailCreditsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPersonDetail(personId: Int) {
        getPersonDetailUseCase(personId = personId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        PersonDetailState(person = result.data)
                }
                is Resource.Error -> {
                    _state.value = PersonDetailState(
                        error = result.statusMessage ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PersonDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}