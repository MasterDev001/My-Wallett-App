package com.example.presenter.signin

import cafe.adriel.voyager.core.model.coroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


internal class SignInViewModelImpl @Inject constructor(
    private val direction: SignInDirection
) : SigInContract.ViewModel {

    override val uiStateFlow = MutableStateFlow(SigInContract.UiState(false))

    private fun reduce(action: (SigInContract.UiState) -> SigInContract.UiState) {
        val oldState = uiStateFlow.value
        uiStateFlow.value = action(oldState)
    }

    override fun onEventDispatcher(intent: SigInContract.Intent) {
        when (intent) {
            is SigInContract.Intent.Login -> {
                reduce { it.copy(isLoading = true) }

            }
            is SigInContract.Intent.ForgotPassword -> {
                coroutineScope.launch { direction.navigateToForgotPsw() }
            }
            is SigInContract.Intent.OpenRegister -> {
                coroutineScope.launch { direction.navigateToRegister() }
            }
        }

    }
}