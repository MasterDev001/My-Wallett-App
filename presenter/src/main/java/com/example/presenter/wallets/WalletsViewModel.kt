package com.example.presenter.wallets

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.WalletData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(WalletsViewModelImpl::class)
interface WalletsViewModel : ScreenModel {

    val wallets: Flow<List<WalletData>>
    val uiState:StateFlow<WalletsContract.UiState>

    fun onEventDispatcher(intent: WalletsContract.Intent)
}