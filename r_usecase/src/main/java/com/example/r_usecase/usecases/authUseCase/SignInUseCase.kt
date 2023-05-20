package com.example.r_usecase.usecases.authUseCase

import com.example.z_entity.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {

     suspend operator fun invoke(email: String, password: String,checkState:Boolean) =
        repository.signInWithEmail(email, password,checkState)
}