package com.example.usecase.repositoryimpl


import com.example.usecase.common.CHILD_EMAIL
import com.example.usecase.common.CHILD_FULLNAME
import com.example.usecase.common.CHILD_ID
import com.example.usecase.common.CHILD_TYPE
import com.example.usecase.common.EMAIL_USERS
import com.example.common.ResultData
import com.example.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth, private val fireStore: FirebaseFirestore
) : AuthRepository {

    override fun registerWithEmail(
        name: String, email: String, password: String
    ): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Loading()

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val dataMap = hashMapOf<String, Any>()
                dataMap[CHILD_EMAIL] = email
                dataMap[CHILD_FULLNAME] = name
                dataMap[CHILD_ID] = auth.uid.toString()
                dataMap[CHILD_TYPE] = EmailAuthProvider.PROVIDER_ID
                fireStore.collection(EMAIL_USERS).document(email).set(dataMap)
                    .addOnSuccessListener { result = ResultData.Success(true) }
                    .addOnFailureListener {
                        result = ResultData.Message(it.message.toString())
                    }
            }.addOnFailureListener { result = ResultData.Message(it.message.toString()) }.await()
        emit(result)
    }.catch { ResultData.Error<Throwable>(it) }.flowOn(Dispatchers.IO)

    override fun signInWithEmail(
        email: String, password: String
    ): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Loading()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result = ResultData.Success(true) }
            .addOnFailureListener { result = ResultData.Message(it.message.toString()) }.await()
        emit(result)
    }.catch { emit(ResultData.Error<Throwable>(it)) }.flowOn(Dispatchers.IO)

    override fun signInWithGoogle(credential: AuthCredential): Flow<ResultData<Any>> {
        return flow<ResultData<Any>> {
            emit(ResultData.Loading())
            val result = auth.signInWithCredential(credential).await()
            emit(ResultData.Success(result))
        }.catch { emit(ResultData.Error(it))}.flowOn(Dispatchers.IO)
    }

    override fun resetPassword(email: String): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun sendEmailVerification(): Flow<ResultData<Any>> = flow {
        var result: ResultData<Any> = ResultData.Success(true)
        auth.currentUser?.sendEmailVerification()
            ?.addOnFailureListener { result = ResultData.Message(it.message.toString()) }?.await()
        emit(result)
    }.catch { emit(ResultData.Error<Throwable>(it)) }.flowOn(Dispatchers.IO)

    override fun signOut(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun deleteAccount(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)

    override fun reloadFirebaseUser(): Flow<ResultData<Any>> = flow<ResultData<Any>> {

    }.catch { }.flowOn(Dispatchers.IO)
}