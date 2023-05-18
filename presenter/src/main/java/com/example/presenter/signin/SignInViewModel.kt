package com.example.presenter.signin

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl


@ScreenModelImpl(SignInViewModelImpl::class)
interface  SignInViewModel : ScreenModel {

    val uiStateFlow: StateFlow<LoginContract.UiState>

     fun onEventDispatcher(intent: LoginContract.Intent)
}