package com.example.presenter.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(HomeViewModelImpl::class)
interface HomeViewModel : ScreenModel {

    val uiState: StateFlow<HomeContract.UiState>

    fun wallets(): List<WalletData>
    fun persons(): List<PersonData>
    fun currencies(): List<CurrencyData>
    fun onEventDispatcher(intent: HomeContract.Intent)
}