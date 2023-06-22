package com.example.presenter.convert

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

internal class ConvertViewModelImpl @Inject constructor(
    private val direction: ConvertDirection,
    private val transactionUseCase: TransactionUseCase,
    private val walletsUseCase: WalletsUseCase,
    private val currencyUseCase: CurrencyUseCase
) : ConvertViewModel {

    override val uiState = MutableStateFlow(ConvertContract.UiState.Default)

    override fun isCurrencyIdExistsInWallet(walletId: String, currencyId: String): Boolean {
        return walletsUseCase.isCurrencyIdExistsInWalletUseC.invoke(walletId, currencyId)
    }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun onEventDispatcher(intent: ConvertContract.Intent) {
        when (intent) {
            is ConvertContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

            is ConvertContract.Intent.ConvertMoney -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val fromCurrency = getCurrency(intent.fromWalletOwner.id)
                    val transaction = TransactionData(
                        date = System.currentTimeMillis().toString(),
                        type = getTypeNumber(Type.CONVERTATION),
                        fromId = intent.fromWallet.id,
                        toId = intent.toWallet.id,
                        currencyId = fromCurrency.id,
                        currencyFrom = fromCurrency.id,
                        currencyTo = intent.toCurrency.id,
                        amount = intent.amount.toDouble(),

                        isFromPocket = true,
                        isToPocket = true,
                        rate = fromCurrency.rate,
                        rateFrom = fromCurrency.rate,
                        rateTo = intent.rate.toDouble(),
                        balance = 0.0
                    )

                    transactionUseCase.convertUseCase.invoke(
                        transaction,
                        intent.amount,
                        intent.fromWalletOwner,
                        intent.fromWallet,
                        intent.toWallet,
                        intent.toCurrency,
                        intent.rate
                    )
                    direction.back()
                }
            }
        }
    }
}