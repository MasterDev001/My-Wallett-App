package com.example.presenter.signin

import android.annotation.SuppressLint
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.ResultData
import com.example.r_usecase.usecases.authUseCase.CheckStateUseCase
import com.example.r_usecase.usecases.authUseCase.GoogleSignUseCase
import com.example.r_usecase.usecases.authUseCase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


internal class SignInViewModelImpl @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val googleSignUseCase: GoogleSignUseCase,
    private val checkStateUseCase: CheckStateUseCase,
    private val direction: SignInDirection
) : SignInViewModel {

    init {
        coroutineScope.launch {
            if (checkStateUseCase.invoke())
                direction.navigateToHome()
        }
    }

    override val uiStateFlow =
        MutableStateFlow<LoginContract.UiState>(LoginContract.UiState.Default)

    @SuppressLint("SuspiciousIndentation")
    override fun onEventDispatcher(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.Login -> {
                uiStateFlow.value = LoginContract.UiState.Loading
                coroutineScope.launch {
                    var response =
                        signInUseCase.invoke(
                            intent.email.trim(),
                            intent.password,
                            intent.checkState
                        )
                    when (response) {
                        is ResultData.Success -> {
                            direction.navigateToHome()
                        }

                        is ResultData.Error -> {

                        }

                        is ResultData.Message<*> -> LoginContract.UiState.Error(response.message.toString())
                        is ResultData.Loading<*> -> LoginContract.UiState.Loading
//                            is ResultData.Error -> LoginContract.UiState.Error(response.message)
                    }
                }
            }

            is LoginContract.Intent.ForgotPassword -> {
                coroutineScope.launch { direction.navigateToForgotPsw() }
            }

            is LoginContract.Intent.OpenRegister -> {
                coroutineScope.launch { direction.navigateToRegister() }
            }

            is LoginContract.Intent.SignWithGoogle -> {
//                reduce { it.copy(isLoading = true) }
                googleSignUseCase.invoke(intent.credential).onEach {
//                    reduce { it.copy(isLoading = false) }
//                    when (it) {
//                        is ResultData.Success -> direction.navigateToHome()
//                        is ResultData.Message<Any?> -> LoginContract.UiState(message = it.message)
//                        is ResultData.Loading -> LoginContract.UiState(isLoading = true)
//                        is ResultData.Error -> LoginContract.UiState(error = it.error)
                }
            }
        }
    }

}
