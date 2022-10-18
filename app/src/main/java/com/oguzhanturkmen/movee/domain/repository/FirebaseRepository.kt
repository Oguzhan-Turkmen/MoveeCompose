package com.oguzhanturkmen.movee.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {
    suspend fun loginUser(authCredential: AuthCredential): FirebaseUser?
}