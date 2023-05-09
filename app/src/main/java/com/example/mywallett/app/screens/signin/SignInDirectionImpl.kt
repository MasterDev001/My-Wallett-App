package com.example.mywallett.app.screens.signin

import com.example.mywallett.app.screens.forgotPsw.ForgotPasswordScreen
import com.example.mywallett.app.screens.home.HomeScreen
import com.example.mywallett.app.screens.register.RegisterScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.signin.SignInDirection
import javax.inject.Inject

class SignInDirectionImpl @Inject constructor(private val appNavigator:AppNavigator):SignInDirection {

    override suspend fun navigateToHome() {
        appNavigator.navigateTo(HomeScreen())
    }

    override suspend fun navigateToForgotPsw() {
        appNavigator.navigateTo(ForgotPasswordScreen())
    }

    override suspend fun navigateToRegister() {
        appNavigator.navigateTo(RegisterScreen())
    }
}