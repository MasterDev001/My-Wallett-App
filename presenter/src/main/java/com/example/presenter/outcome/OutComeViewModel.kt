package com.example.presenter.outcome

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(OutComeViewModelImpl::class)
interface OutComeViewModel : ScreenModel {

    val uiState: StateFlow<OutComeContract.UiState>
    val wallets:Flow<List<WalletData>>

    fun onEventDispatcher(intent: OutComeContract.Intent)
    fun getWalletOwnerListByWalletId(walletId:String): Flow<List<WalletOwnerData>>
    fun getCurrency(id: String): CurrencyData
}