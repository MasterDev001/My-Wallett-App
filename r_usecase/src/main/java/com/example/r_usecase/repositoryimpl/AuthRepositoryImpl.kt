package com.example.r_usecase.repositoryimpl

import com.example.a_common.ResultData
import com.example.r_usecase.common.CHILD_EMAIL
import com.example.r_usecase.common.CHILD_FULLNAME
import com.example.r_usecase.common.CHILD_ID
import com.example.r_usecase.common.CHILD_TYPE
import com.example.r_usecase.common.USERS
import com.example.z_entity.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth, private val fireStore: FirebaseFirestore,
) : AuthRepository {

    override var checkState: Boolean = true
    override var currentUser: FirebaseUser? = auth.currentUser

    override fun registerWithEmail(
        name: String, email: String, password: String
    ): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Loading()

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            currentUser = it.user
            val dataMap = hashMapOf<String, Any>()
            dataMap[CHILD_EMAIL] = email
            dataMap[CHILD_FULLNAME] = name
            dataMap[CHILD_ID] = auth.uid.toString()
            dataMap[CHILD_TYPE] = EmailAuthProvider.PROVIDER_ID
            fireStore.collection(USERS).document(email).set(dataMap)
                .addOnSuccessListener { result = ResultData.Success() }.addOnFailureListener {
                    result = ResultData.Message<Any>(it.message.toString())
                }
        }.addOnFailureListener { result = ResultData.Message<Any>(it.message.toString()) }.await()
        emit(result)
    }.catch { emit(ResultData.Message(it.message.toString())) }.flowOn(Dispatchers.IO)

    override suspend fun signInWithEmail(
        email: String, password: String, checkState: Boolean
    ): ResultData<Any> {
        return try {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                currentUser = it.user
            }.await()
            this.checkState = checkState
            ResultData.Success()
        } catch (e: Exception) {
            ResultData.Message(e.message.toString())
        }
    }

    override fun signInWithGoogle(credential: AuthCredential): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Loading()
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                result = ResultData.Success()
                currentUser = it.user
            }
            .addOnFailureListener { result = ResultData.Message<Any>(it.message.toString()) }
            .await().user
        emit(result)
    }.catch { emit(ResultData.Message(it.message.toString())) }.flowOn(Dispatchers.IO)

    override fun resetPassword(email: String): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun sendEmailVerification(): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Success()
        auth.currentUser?.sendEmailVerification()
            ?.addOnFailureListener { result = ResultData.Message<Any>(it.message.toString()) }
            ?.await()
        emit(result)
    }.catch { emit(ResultData.Message(it.message.toString())) }.flowOn(Dispatchers.IO)

    override fun signOut(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun deleteAccount(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun reloadFirebaseUser(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)
}