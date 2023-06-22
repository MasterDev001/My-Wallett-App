package com.example.presenter.lend

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(LendViewModelImpl::class)
interface LendViewModel : ScreenModel {

    val uiState: StateFlow<LendContract.UiState>

    fun isCurrencyIdExistsInWallet(walletId: String, currencyId: String): Boolean
    fun onEventDispatcher(intent: LendContract.Intent)
    fun getCurrency(id: String): CurrencyData
}