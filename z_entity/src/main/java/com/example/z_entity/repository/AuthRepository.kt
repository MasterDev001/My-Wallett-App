package com.example.z_entity.repository

import com.example.a_common.ResultData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val currentUser:FirebaseUser?
    val checkedState:Boolean

    fun registerWithEmail(name: String, email: String, password: String): Task<AuthResult>
    suspend fun signInWithEmail(email: String, password: String,checkState:Boolean): Task<AuthResult>
    fun signInWithGoogle(credential: AuthCredential): Flow<ResultData<Any>>
    fun resetPassword(email: String): Flow<ResultData<Any>>
    fun sendEmailVerification(): Flow<ResultData<Any>>
    fun signOut(): Flow<ResultData<Any>>
    fun deleteAccount(): Flow<ResultData<Any>>
    fun reloadFirebaseUser(): Flow<ResultData<Any>>

}