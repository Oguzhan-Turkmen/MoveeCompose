package com.oguzhanturkmen.movee.data.firebase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface FireBaseAuthLoginSource {
    suspend fun loginWithCredential2(authCredential: AuthCredential): FirebaseUser?
}