package com.example.presenter.lend

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

internal class LendViewModelImpl @Inject constructor(
    private val direction: LendDirection,
    private val transactionUseCase: TransactionUseCase,
    private val currencyUseCase: CurrencyUseCase,
    private val walletsUseCase: WalletsUseCase
) : LendViewModel {

    override val uiState = MutableStateFlow(LendContract.UiState.Default)

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun isCurrencyIdExistsInWallet(walletId: String, currencyId: String): Boolean {
        return walletsUseCase.isCurrencyIdExistsInWalletUseC.invoke(walletId, currencyId)
    }

    override fun onEventDispatcher(intent: LendContract.Intent) {
        when (intent) {
            is LendContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

            is LendContract.Intent.LendMoney -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val transaction = TransactionData(
                        date = System.currentTimeMillis().toString(),
                        type = getTypeNumber(Type.LEND),
                        fromId = intent.wallet.id,
                        toId = intent.personData.id,
                        currencyId = intent.currencyData.id,
                        amount = intent.amount.toDouble(),
                        comment = intent.comment,

                        isFromPocket = true,
                        isToPocket = false,
                        rate = intent.currencyData.rate,
                        rateFrom = intent.currencyData.rate,
                        rateTo = intent.currencyData.rate,
                        balance = 0.0
                    )

                    transactionUseCase.lendUseCase.invoke(
                        intent.amount,
                        intent.personData,
                        intent.wallet,
                        intent.currencyData,
                        intent.selectedWalletOwner,
                        transaction
                    )
                }
            }
        }
    }
}