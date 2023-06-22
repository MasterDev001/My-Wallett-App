package com.example.presenter.borrow

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.Type
import com.example.a_common.data.TransactionData
import com.example.a_common.getTypeNumber
import com.example.r_usecase.usecases.transactionUseCase.TransactionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class BorrowViewModelImpl @Inject constructor(
    private val direction: BorrowDirection,
    private val transactionUseCase: TransactionUseCase
) : BorrowViewModel {

    override val uiState = MutableStateFlow(BorrowContract.UiState.Default)

    override fun onEventDispatcher(intent: BorrowContract.Intent) {
        when (intent) {
            is BorrowContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

            is BorrowContract.Intent.BorrowMoney -> {
                coroutineScope.launch(Dispatchers.IO) {
                    val transaction = TransactionData(
                        date = System.currentTimeMillis().toString(),
                        type = getTypeNumber(Type.BORROW),
                        fromId = "",
                        toId = intent.wallet.id,
                        currencyId = intent.currencyData.id,
                        amount = intent.amount.toDouble(),
                        comment = intent.comment,

                        isFromPocket = false,
                        isToPocket = true,
                        rate = intent.currencyData.rate,
                        rateFrom = intent.currencyData.rate,
                        rateTo = intent.currencyData.rate,
                        balance = 0.0
                    )

                    transactionUseCase.borrowUseCase.invoke(
                        intent.amount,
                        intent.personData,
                        intent.wallet,
                        intent.currencyData,
                        transaction
                    )
                }
            }
        }
    }
}