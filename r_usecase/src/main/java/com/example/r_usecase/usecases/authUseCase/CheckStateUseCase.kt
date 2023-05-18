package com.example.r_usecase.usecases.authUseCase

import android.util.Log

import com.example.repository.AuthRepository
import javax.inject.Inject

class CheckStateUseCase @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke(): Boolean {
        Log.d("TAG12", "invoke: ${repository.currentUser} ${repository.checkState}")
        return repository.currentUser != null && repository.checkState
    }
}