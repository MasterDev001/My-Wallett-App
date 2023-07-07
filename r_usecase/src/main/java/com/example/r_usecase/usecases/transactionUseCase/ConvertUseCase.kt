package com.example.r_usecase.usecases.transactionUseCase

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.TransactionData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import com.example.z_entity.db.entity.toMyTransaction
import com.example.z_entity.repository.TransactionRepository
import javax.inject.Inject

class ConvertUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    val walletsUseCase: WalletsUseCase
) {

    suspend operator fun invoke(
        transaction: TransactionData,
        amount: String,
        fromWalletOwner: WalletOwnerData,
        fromWallet: WalletData,
        toWallet: WalletData,
        toCurrency: CurrencyData,
        rate: String
    ) {
        transactionRepository.add(transaction.toMyTransaction())

        walletsUseCase.outComeUseCase.invoke(
            amount.toDouble(),
            fromWallet,
            fromWalletOwner,
        )
        walletsUseCase.inComeUseCase.invoke(
            amount.toDouble() * rate.toDouble(),
            toCurrency,
            toWallet
        )
    }
}