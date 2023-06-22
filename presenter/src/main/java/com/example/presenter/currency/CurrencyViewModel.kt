package com.example.presenter.currency

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(CurrencyViewModelImpl::class)
interface CurrencyViewModel : ScreenModel {

    val currencies: Flow<List<CurrencyData>>
    val uiState: StateFlow<CurrencyContract.UiState>

    fun onEventDispatcher(intent: CurrencyContract.Intent)
    fun isCurrencyExist(name: String): Boolean
}