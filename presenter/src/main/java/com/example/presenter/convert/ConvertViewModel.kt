package com.example.presenter.convert

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(ConvertViewModelImpl::class)
interface ConvertViewModel : ScreenModel {

    val uiState: StateFlow<ConvertContract.UiState>

    fun onEventDispatcher(intent: ConvertContract.Intent)
    fun isCurrencyIdExistsInWallet(walletId: String, currencyId: String): Boolean
    fun getCurrency(id: String): CurrencyData
}