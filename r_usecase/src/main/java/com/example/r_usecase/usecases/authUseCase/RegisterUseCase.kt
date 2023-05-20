package com.example.r_usecase.usecases.authUseCase

import com.example.z_entity.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {

     operator fun invoke(name: String, email: String, password: String) =
        repository.registerWithEmail(name, email, password)
}