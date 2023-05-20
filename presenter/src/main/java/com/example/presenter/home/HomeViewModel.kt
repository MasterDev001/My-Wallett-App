package com.example.presenter.home

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(HomeViewModelImpl::class)
interface HomeViewModel : ScreenModel {

    val uiState: StateFlow<HomeContract.UiState>
    fun onEventDispatcher(intent: HomeContract.Intent)
}