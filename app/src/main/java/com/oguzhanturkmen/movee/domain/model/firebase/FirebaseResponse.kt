package com.oguzhanturkmen.movee.domain.model.firebase

sealed class FirebaseResponse<out T> {
    object Loading : FirebaseResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ) : FirebaseResponse<T>()

    data class Failure(
        val e: Exception
    ) : FirebaseResponse<Nothing>()
}