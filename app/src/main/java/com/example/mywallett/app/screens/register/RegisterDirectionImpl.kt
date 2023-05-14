package com.example.mywallett.app.screens.register

import com.example.mywallett.app.screens.home.HomeScreen
import com.example.mywallett.app.screens.signin.SignInScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.signUp.SignUpDirection
import javax.inject.Inject

class RegisterDirectionImpl @Inject constructor(private val appNavigator: AppNavigator) :
    SignUpDirection {

    override suspend fun navigateToHome() {
        appNavigator.navigateTo(HomeScreen())
    }

    override suspend fun navigateToSigIn() {
        appNavigator.navigateTo(SignInScreen())
    }
}