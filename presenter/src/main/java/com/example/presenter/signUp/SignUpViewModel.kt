package com.example.presenter.signUp

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(SignUpViewModelImpl::class)
interface SignUpViewModel:ScreenModel {

    val uiStateFlow:StateFlow<SignUpContract.UiState>
    fun onEventDispatcher(intent: SignUpContract.Intent)
}