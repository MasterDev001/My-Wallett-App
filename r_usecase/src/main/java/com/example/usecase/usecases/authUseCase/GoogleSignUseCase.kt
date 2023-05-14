package com.example.usecase.usecases.authUseCase

import com.example.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

class GoogleSignUseCase @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke(credential: AuthCredential) = repository.signInWithGoogle(credential)
}