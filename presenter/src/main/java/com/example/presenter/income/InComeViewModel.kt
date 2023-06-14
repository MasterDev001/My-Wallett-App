package com.example.presenter.income

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(InComeViewModelImpl::class)
interface InComeViewModel : ScreenModel {

    val wallets: Flow<List<WalletData>>
    val currencies: Flow<List<CurrencyData>>
    val uiState: StateFlow<InComeContract.UiState>

    fun getWalletOwnerListByWalletId(walletId:String): Flow<List<WalletOwnerData>>
    fun getCurrency(id: String): CurrencyData
    fun onEventDispatcher(intent: InComeContract.Intent)
}