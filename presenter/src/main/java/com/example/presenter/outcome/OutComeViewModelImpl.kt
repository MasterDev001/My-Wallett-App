package com.example.presenter.outcome

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class OutComeViewModelImpl @Inject constructor(
    private val walletsUseCase: WalletsUseCase,
    private val currencyUseCase: CurrencyUseCase,
    private val direction: OutComeDirection
) : OutComeViewModel {

    override val uiState =
        MutableStateFlow<OutComeContract.UiState>(OutComeContract.UiState.Default)

    override val wallets: Flow<List<WalletData>> = flow {
        emitAll(walletsUseCase.getAllWalletsUseC.invoke())
    }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun getWalletOwnerListByWalletId(walletId: String): Flow<List<WalletOwnerData>> =
        flow {
            emitAll(walletsUseCase.getWalletOwnerListUseC.invoke(walletId))
        }

    override fun onEventDispatcher(intent: OutComeContract.Intent) {
        when (intent) {
            is OutComeContract.Intent.OpenOutComeCurrencies -> {
                coroutineScope.launch { direction.navigateToOutComeCurrencies(intent.wallet) }
            }

            is OutComeContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }
        }
    }
}