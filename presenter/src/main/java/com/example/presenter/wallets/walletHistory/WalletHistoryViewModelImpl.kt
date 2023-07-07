package com.example.presenter.wallets.walletHistory

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.WalletOwnerData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.historyUseCase.HistoryUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class WalletHistoryViewModelImpl @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val direction: WalletHistoryDirection,
    private val currencyUseCase: CurrencyUseCase,
    private val walletsUseCase: WalletsUseCase
) : WalletHistoryViewModel {

    override val uiState = MutableStateFlow(WalletHistoryContract.UiState(false))

    override fun walletHistoryPager(ownerId: String): Flow<PagingData<HistoryData>> =
        flow {
            emitAll(historyUseCase.getHistoryByOwnerIdUseC.invoke(ownerId))
        }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun getWalletOwnerListByWalletId(walletId: String): Flow<List<WalletOwnerData>> =
        flow {
            emitAll(walletsUseCase.getWalletOwnerListUseC.invoke(walletId))
        }

    override fun onEventDispatcher(intent: WalletHistoryContract.Intent) {
        when (intent) {
            is WalletHistoryContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

        }
    }
}