package com.example.presenter.outCome_currencies

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(OutComeCurrenciesViewMImpl::class)
interface OutComeCurrenciesViewM : ScreenModel {

    val uiState:StateFlow<OutComeCurrenciesContract.UiState>

    fun onEventDispatcher(intent:OutComeCurrenciesContract.Intent)
    fun getCurrency(id: String): CurrencyData
}