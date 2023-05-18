package com.example.presenter.signin

import com.google.firebase.auth.AuthCredential


class LoginContract {

    sealed interface Intent {
        class Login(
            val email: String, val password: String, val checkState: Boolean = false) :
            Intent

        class SignWithGoogle(val credential: AuthCredential) : Intent
        object ForgotPassword : Intent
        object OpenRegister : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val message: String = "") : UiState
    }
}
