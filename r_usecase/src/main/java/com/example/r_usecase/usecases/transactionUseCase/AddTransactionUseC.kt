package com.example.r_usecase.usecases.transactionUseCase

import com.example.a_common.data.TransactionData
import com.example.a_common.data.WalletData
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import com.example.z_entity.db.entity.toMyTransaction
import com.example.z_entity.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseC @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val walletsUseCase: WalletsUseCase
) {

    suspend operator fun invoke(transactionData: TransactionData, wallet: WalletData): Long {
//        walletsUseCase.
         return transactionRepository.add(transactionData.toMyTransaction())
    }
}