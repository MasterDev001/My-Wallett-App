package com.example.r_usecase.usecases.authUseCase

import com.example.a_common.ResultData
import com.example.z_entity.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(
        email: String,
        password: String,
        checkState: Boolean
    ): Flow<ResultData<Any>> = flow {
        try {
//            emit(ResultData.Loading())
            repository.signInWithEmail(email, password, checkState).await()
            emit(ResultData.Success())
        } catch (e: IOException) {
            emit(ResultData.Message("internet ${e.localizedMessage}"))
        }
    }
}