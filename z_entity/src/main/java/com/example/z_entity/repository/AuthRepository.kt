package com.example.z_entity.repository

import com.example.a_common.ResultData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val currentUser:FirebaseUser?
    val checkState:Boolean

    fun registerWithEmail(name: String, email: String, password: String): Flow<ResultData<Any>>
    suspend fun signInWithEmail(email: String, password: String,checkState:Boolean): ResultData<Any>
    fun signInWithGoogle(credential: AuthCredential): Flow<ResultData<Any>>
    fun resetPassword(email: String): Flow<ResultData<Any>>
    fun sendEmailVerification(): Flow<ResultData<Any>>
    fun signOut(): Flow<ResultData<Any>>
    fun deleteAccount(): Flow<ResultData<Any>>
    fun reloadFirebaseUser(): Flow<ResultData<Any>>

}