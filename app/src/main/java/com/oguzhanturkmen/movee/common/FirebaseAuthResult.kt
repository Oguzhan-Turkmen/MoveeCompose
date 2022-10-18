package com.oguzhanturkmen.movee.common

import com.google.firebase.auth.FirebaseAuthException

sealed class FirebaseAuthResult<AuthenticationState>(
    val authenticationState: AuthenticationState,
    val firebaseException: FirebaseAuthException? = null
) {
    class Success<AuthenticationState>(
        authenticationState: AuthenticationState,
    ) : FirebaseAuthResult<AuthenticationState>(authenticationState)

    class Failure<AuthenticationState>(
        authenticationState: AuthenticationState,
        firebaseException: FirebaseAuthException?
    ) : FirebaseAuthResult<AuthenticationState>(
        authenticationState,
        firebaseException
    )

    class InProgress<AuthenticationState>(
        authenticationState: AuthenticationState,
    ) : FirebaseAuthResult<AuthenticationState>(authenticationState)
}