package com.example.r_usecase.usecases.authUseCase

import com.example.z_entity.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke() = repository.signOut()
}