package com.example.r_usecase.usecases.authUseCase

import com.example.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {

     operator fun invoke(email: String, password: String) =
        repository.signInWithEmail(email, password)
}