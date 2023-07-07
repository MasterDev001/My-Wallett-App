package com.example.presenter.wallets

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class WalletsViewModelImpl @Inject constructor(
    private val direction: WalletsDirection,
    private val walletsUseCase: WalletsUseCase,
    private val currencyUseCase: CurrencyUseCase
) : WalletsViewModel {

    override val wallets: Flow<List<WalletData>> = flow {
        emitAll(walletsUseCase.getAllWalletsUseC.invoke())
    }
    override val uiState =
        MutableStateFlow<WalletsContract.UiState>(WalletsContract.UiState.Default)

    override fun getWalletOwnerListByWalletId(walletId: String): Flow<List<WalletOwnerData>> =
        flow {
            emitAll(walletsUseCase.getWalletOwnerListUseC.invoke(walletId))
        }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun isWalletExist(name: String): Boolean {
        return walletsUseCase.isWalletExist.invoke(name)
    }

    override fun onEventDispatcher(intent: WalletsContract.Intent) {
        when (intent) {
            is WalletsContract.Intent.AddWallet -> {
                uiState.value = WalletsContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    walletsUseCase.addWalletUseC.invoke(intent.walletData)
                }
            }

            is WalletsContract.Intent.DeleteWallet -> {
                uiState.value = WalletsContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    walletsUseCase.deleteWalletUseC.invoke(intent.walletData)
                }
            }

            is WalletsContract.Intent.UpdateWallet -> {
                uiState.value = WalletsContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    walletsUseCase.updateWalletUseC.invoke(intent.walletData)
                }
            }

            is WalletsContract.Intent.OpenHome -> {
                coroutineScope.launch {
                    direction.back()
                }
            }

            is WalletsContract.Intent.OpenWalletHistory -> {
                coroutineScope.launch { direction.navigateToWalletHistory(intent.walletData) }
            }
        }
    }
}