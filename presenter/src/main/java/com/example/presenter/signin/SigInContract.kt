package com.example.presenter.signin

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

internal interface SigInContract {

    sealed interface Intent {
        sealed class Login(val email: String, val password: String) : Intent
        object ForgotPassword : Intent
        object OpenRegister : Intent
    }

    data class UiState(
        val isLoading: Boolean? = null,
        val message: String? = null,
        val error: String? = null
    )

    @ScreenModelImpl(SignInViewModelImpl::class)
    interface ViewModel : ScreenModel {

        val uiStateFlow: StateFlow<SigInContract.UiState>

        fun onEventDispatcher(intent: SigInContract.Intent)
    }
}