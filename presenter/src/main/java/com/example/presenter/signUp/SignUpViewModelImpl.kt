package com.example.presenter.signUp

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.ResultData
import com.example.r_usecase.usecases.authUseCase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SignUpViewModelImpl @Inject constructor(
    private val direction: SignUpDirection,
    private val signUpUseCase: RegisterUseCase
) : SignUpViewModel {

    override val uiStateFlow = MutableStateFlow(SignUpContract.UiState(false))

    private fun reduce(action: (SignUpContract.UiState) -> SignUpContract.UiState) {
        val oldState = uiStateFlow.value
        uiStateFlow.value = action(oldState)
    }

    override fun onEventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.Register -> {
                reduce { it.copy(isLoading = true) }
                signUpUseCase.invoke(intent.name.trim(), intent.email.trim(), intent.password)
                    .onEach {
                        reduce { it.copy(isLoading = false) }
                        when (it) {
                            is ResultData.Loading -> {
                                SignUpContract.UiState(isLoading = true)
                            }

                            is ResultData.Success -> {
                                coroutineScope.launch {
                                    direction.navigateToHome()
                                }
                            }

                            is ResultData.Error<*> -> {
                                SignUpContract.UiState(message = it.message)
                            }

//                            is ResultData.Error -> {
//                                SignUpContract.UiState(error = it.error)
//                            }
                        }
                    }.launchIn(coroutineScope)
            }

            is SignUpContract.Intent.OpenSignIn -> {
                coroutineScope.launch { direction.navigateToSigIn() }
            }

            is SignUpContract.Intent.OpenRegister -> {

            }
        }
    }

}