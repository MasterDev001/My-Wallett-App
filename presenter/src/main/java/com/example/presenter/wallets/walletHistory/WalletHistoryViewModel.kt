package com.example.presenter.wallets.walletHistory

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.WalletOwnerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(WalletHistoryViewModelImpl::class)
interface WalletHistoryViewModel : ScreenModel {

    val uiState : StateFlow<WalletHistoryContract.UiState>

    fun getCurrency(id: String): CurrencyData
    fun walletHistoryPager(ownerId:String) : Flow<PagingData<HistoryData>>
    fun getWalletOwnerListByWalletId(walletId:String): Flow<List<WalletOwnerData>>
    fun onEventDispatcher(intent: WalletHistoryContract.Intent)
}