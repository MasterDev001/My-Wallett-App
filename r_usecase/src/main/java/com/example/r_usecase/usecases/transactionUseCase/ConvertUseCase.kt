package com.example.r_usecase.usecases.transactionUseCase

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConvertUseCase @Inject constructor(
    private val walletsUseCase: WalletsUseCase,
) {

    suspend operator fun invoke(
        amount: String,
        fromWalletOwner: WalletOwnerData,
        fromWallet: WalletData,
        toWallet: WalletData,
        fromCurrency: CurrencyData,
        toCurrency: CurrencyData,
        rate: String
    ): Flow<Boolean> = flow {
        coroutineScope {
            walletsUseCase.outComeUseCase.invoke(
                amount.toDouble(),
                fromWallet,
                fromWalletOwner,
                fromCurrency,
            )
            walletsUseCase.inComeUseCase.invoke(
                amount.toDouble() * rate.toDouble(),
                toCurrency,
                toWallet,
            )
        }
        emit(true)
    }
}