package com.oguzhanturkmen.movee.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.oguzhanturkmen.movee.common.Resource

interface FirebaseRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}