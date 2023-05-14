package com.example.presenter.signin

import com.google.firebase.auth.AuthCredential


class LoginContract {

    sealed interface Intent {
        class Login(val email: String, val password: String) : Intent
        class SignWithGoogle(val credential: AuthCredential):Intent
        object ForgotPassword : Intent
        object OpenRegister : Intent
    }

    data class UiState(
        val isLoading: Boolean? = null,
        val message: String? = null,
        val error: Throwable? = null
    )
}
