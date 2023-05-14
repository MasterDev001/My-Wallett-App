package com.example.usecase.usecases.authUseCase

import com.example.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke() = repository.signOut()
}