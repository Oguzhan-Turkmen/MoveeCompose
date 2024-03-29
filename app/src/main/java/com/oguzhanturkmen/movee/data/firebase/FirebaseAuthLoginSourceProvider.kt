package com.oguzhanturkmen.movee.data.firebase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthLoginSourceProvider @Inject constructor() : FireBaseAuthLoginSource {
    override suspend fun loginWithCredential2(authCredential: AuthCredential): FirebaseUser? {
        val firebaseAuthInstance = FirebaseAuth.getInstance()
        firebaseAuthInstance.signInWithCredential(authCredential)
        return firebaseAuthInstance.currentUser ?: throw FirebaseAuthException("", "")
    }
}