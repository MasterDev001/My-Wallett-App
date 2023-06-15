package com.example.presenter.income

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.Type
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.TransactionData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.a_common.getTypeNumber
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.transactionUseCase.TransactionUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

internal class InComeViewModelImpl @Inject constructor(
    private val direction: InComeDirection,
    private val walletsUseCase: WalletsUseCase,
    private val currencyUseCase: CurrencyUseCase,
    private val transactionUseCase: TransactionUseCase
) : InComeViewModel {

    override val wallets: Flow<List<WalletData>> = flow {
        emitAll(walletsUseCase.getAllWalletsUseC.invoke())
    }
    override val currencies: Flow<List<CurrencyData>> = flow {
        emitAll(currencyUseCase.getAllCurrencies.invoke())
    }
    override val uiState = MutableStateFlow<InComeContract.UiState>(InComeContract.UiState.Default)

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun getWalletOwnerListByWalletId(walletId: String): Flow<List<WalletOwnerData>> =
        flow {
            emitAll(walletsUseCase.getWalletOwnerListUseC.invoke(walletId))
        }

    override fun onEventDispatcher(intent: InComeContract.Intent) {
        when (intent) {
            is InComeContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

            is InComeContract.Intent.AddWallet -> {
                coroutineScope.launch(Dispatchers.IO) {
                    walletsUseCase.addWalletUseC.invoke(intent.walletData)
                }
            }

            is InComeContract.Intent.UpdateWallet -> {
                coroutineScope.launch(Dispatchers.IO) {
                    walletsUseCase.updateWalletUseC.invoke(intent.walletData)
                }
            }

            is InComeContract.Intent.IncomeMoney -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val transaction = TransactionData(
                        id = UUID.randomUUID().toString(),
                        type = getTypeNumber(Type.INCOME),
                        fromId = "",
                        toId = intent.wallet.id,
                        currencyId = intent.currencyData.id,
                        amount = intent.amount,
                        date = System.currentTimeMillis(),
                        comment = intent.comment,

                        isFromPocket = false,
                        isToPocket = true,
                        rate = intent.currencyData.rate,
                        rateFrom = intent.currencyData.rate,
                        rateTo = intent.currencyData.rate,
                        balance = 0.0
                    )
                    transactionUseCase.addTransaction.invoke(transaction, intent.wallet)

                    walletsUseCase.inComeUseCase.invoke(
                        intent.amount,
                        intent.currencyData,
                        intent.wallet

                    )
                }
            }
        }
    }
}