package com.oguzhanturkmen.movee.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.oguzhanturkmen.movee.data.firebase.FirebaseAuthLoginSourceProvider
import com.oguzhanturkmen.movee.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(private val firebaseAuthLoginSourceProvider: FirebaseAuthLoginSourceProvider) :
    FirebaseRepository {
    override suspend fun loginUser(authCredential: AuthCredential): FirebaseUser? {
        return firebaseAuthLoginSourceProvider.loginWithCredential2(authCredential)
    }
}