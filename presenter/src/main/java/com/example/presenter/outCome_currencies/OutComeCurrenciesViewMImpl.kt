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
import java.util.UUID
import javax.inject.Inject

internal class OutComeCurrenciesViewMImpl @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
    private val direction: OutComeCurrenciesDirection,
    private val transactionUseCase: TransactionUseCase,
    private val walletsUseCase: WalletsUseCase
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

                    walletsUseCase.outComeUseCase.invoke(
                        intent.amount,
                        intent.wallet,
                        intent.currentWalletOwner,
                        intent.currentWalletOwnerIndex
                    )
                }
            }

            is OutComeCurrenciesContract.Intent.OpenOutCome -> {
                coroutineScope.launch { direction.back() }
            }
        }
    }


}