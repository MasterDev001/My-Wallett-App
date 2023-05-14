package com.example.repository

import com.example.common.ResultData
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun registerWithEmail(name: String, email: String, password: String): Flow<ResultData<Any>>
    fun signInWithEmail(email: String, password: String): Flow<ResultData<Any>>
    fun signInWithGoogle(credential: AuthCredential): Flow<ResultData<Any>>
    fun resetPassword(email: String): Flow<ResultData<Any>>
    fun sendEmailVerification(): Flow<ResultData<Any>>
    fun signOut(): Flow<ResultData<Any>>
    fun deleteAccount(): Flow<ResultData<Any>>
    fun reloadFirebaseUser(): Flow<ResultData<Any>>

}