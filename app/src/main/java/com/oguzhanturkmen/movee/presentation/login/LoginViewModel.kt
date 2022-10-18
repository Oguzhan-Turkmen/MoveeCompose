package com.oguzhanturkmen.movee.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.oguzhanturkmen.movee.common.AuthenticationState
import com.oguzhanturkmen.movee.common.FirebaseAuthResult
import com.oguzhanturkmen.movee.domain.useCase.FirebaseAuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val firebaseAuthUseCase: FirebaseAuthUseCase) :
    ViewModel() {
    var authState = mutableStateOf<FirebaseAuthResult<AuthenticationState>?>(null)
        private set

    fun loginWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            firebaseAuthUseCase.invoke(authCredential).collect {
                authState.value = it
            }
        }
    }
}