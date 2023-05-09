package com.example.presenter.signin

interface SignInDirection {

    suspend fun navigateToHome()
    suspend fun navigateToForgotPsw()
    suspend fun navigateToRegister()
}