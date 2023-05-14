package com.example.presenter.signUp

class SignUpContract {

    sealed interface Intent {
        class Register(val name: String, val email: String, val password: String) : Intent
        object OpenRegister : Intent
        object OpenSignIn : Intent
    }
        data class UiState(
            val isLoading: Boolean? = null,
            val message: String? = null,
            val error: Throwable? = null
        )

}