package com.example.presenter.signin

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.common.ResultData
import com.example.r_usecase.usecases.authUseCase.GoogleSignUseCase
import com.example.r_usecase.usecases.authUseCase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


internal class SignInViewModelImpl @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val googleSignUseCase: GoogleSignUseCase,
    private val direction: SignInDirection
) : SignInViewModel {

    override val uiStateFlow = MutableStateFlow(LoginContract.UiState(isLoading = false))

    private fun reduce(action: (LoginContract.UiState) -> LoginContract.UiState) {
        val oldState = uiStateFlow.value
        uiStateFlow.value = action(oldState)
    }

    override fun onEventDispatcher(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.Login -> {
                reduce { it.copy(isLoading = true) }
                signInUseCase.invoke(intent.email, intent.password).onEach {
                        reduce { it.copy(isLoading = false) }
                        when (it) {
                            is ResultData.Success -> direction.navigateToHome()
                            is ResultData.Message -> LoginContract.UiState(message = it.message)
                            is ResultData.Loading -> LoginContract.UiState(isLoading = true)
                            is ResultData.Error -> LoginContract.UiState(error = it.error)
                        }
                    }.launchIn(coroutineScope)
            }

            is LoginContract.Intent.ForgotPassword -> {
                coroutineScope.launch { direction.navigateToForgotPsw() }
            }

            is LoginContract.Intent.OpenRegister -> {
                coroutineScope.launch { direction.navigateToRegister() }
            }

            is LoginContract.Intent.SignWithGoogle -> {
                reduce { it.copy(isLoading = true) }
                googleSignUseCase.invoke(intent.credential).onEach {
                    reduce { it.copy(isLoading = false) }
                    when (it) {
                        is ResultData.Success -> direction.navigateToHome()
                        is ResultData.Message -> LoginContract.UiState(message = it.message)
                        is ResultData.Loading -> LoginContract.UiState(isLoading = true)
                        is ResultData.Error -> LoginContract.UiState(error = it.error)
                    }
                }
            }
        }

    }
}