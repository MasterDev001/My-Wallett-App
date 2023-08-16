package com.example.presenter.outCome_currencies

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.Type
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.TransactionData
import com.example.a_common.getTypeNumber
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.transactionUseCase.TransactionUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class OutComeCurrenciesViewMImpl @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
    private val direction: OutComeCurrenciesDirection,
    private val walletsUseCase: WalletsUseCase,
    private val transactionUseCase: TransactionUseCase
) : OutComeCurrenciesViewM {

    override val uiState =
        MutableStateFlow<OutComeCurrenciesContract.UiState>(OutComeCurrenciesContract.UiState.Default)

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun onEventDispatcher(intent: OutComeCurrenciesContract.Intent) {
        when (intent) {
            is OutComeCurrenciesContract.Intent.OutComeMoney -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val transaction = TransactionData(
                        date = System.currentTimeMillis().toString(),
                        type = getTypeNumber(Type.OUTCOME),
                        fromId = intent.wallet.id,
                        toId = "",
                        currencyId = intent.currencyData.id,
                        amount = intent.amount,
                        comment = intent.comment,

                        isFromPocket = true,
                        isToPocket = false,
                        rate = intent.currencyData.rate,
                        rateFrom = intent.currencyData.rate,
                        rateTo = intent.currencyData.rate,
                        balance = 0.0
                    )
                    transactionUseCase.addTransaction.invoke(transaction)

                    walletsUseCase.outComeUseCase.invoke(
                        intent.amount,
                        intent.wallet,
                        intent.currentWalletOwner,
                        intent.currencyData,
                    )
                }
            }

            is OutComeCurrenciesContract.Intent.OpenOutCome -> {
                coroutineScope.launch { direction.back() }
            }
        }
    }


}