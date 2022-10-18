package com.oguzhanturkmen.movee.domain.useCase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.oguzhanturkmen.movee.common.AuthenticationState
import com.oguzhanturkmen.movee.common.FirebaseAuthResult
import com.oguzhanturkmen.movee.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseAuthUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    suspend operator fun invoke(authCredential: AuthCredential) = flow {
        emit(FirebaseAuthResult.InProgress(AuthenticationState.IN_PROGRESS))
        try {
            firebaseRepository.loginUser(authCredential)?.let {
                emit(FirebaseAuthResult.Success(AuthenticationState.AUTHENTICATED))
            } ?: run {
                emit(FirebaseAuthResult.Failure(AuthenticationState.UNAUTHENTICATED, null))
            }
        } catch (e: FirebaseAuthException) {
            emit(FirebaseAuthResult.Failure(AuthenticationState.UNAUTHENTICATED, e))
        }
    }
}