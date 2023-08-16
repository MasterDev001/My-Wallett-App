package com.example.presenter.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerDataList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(HomeViewModelImpl::class)
interface HomeViewModel : ScreenModel {

    val uiState: StateFlow<HomeContract.UiState>
    val getAllBalance:Flow<List<WalletOwnerDataList>>

    fun getCurrency(id: String): CurrencyData
    fun wallets(): List<WalletData>
    fun persons(): List<PersonData>
    fun currencies(): List<CurrencyData>
    fun getLimitedHistory(count:Int):Flow<List<HistoryData>>
    fun onEventDispatcher(intent: HomeContract.Intent)
}