package com.example.r_usecase.usecases.authUseCase

import com.example.a_common.ResultData
import com.example.z_entity.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke(name: String, email: String, password: String): Flow<ResultData<Any>> =
        flow {
            try {
//                emit(ResultData.Loading())
                var result: ResultData<Any> = ResultData.Loading()
                repository.registerWithEmail(name, email, password).addOnSuccessListener {
                result=ResultData.Success()
                }.addOnFailureListener { result=ResultData.Message("hato") }.await()
                emit(result)
            } catch (e: Exception) {
                emit(ResultData.Message(e.message))
            }
        }
}