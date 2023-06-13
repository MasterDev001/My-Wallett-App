package com.example.r_usecase.usecases.transactionUseCase

import com.example.a_common.data.TransactionData
import com.example.z_entity.db.entity.toTransactionData
import com.example.z_entity.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTransactionsUseC @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(): Flow<List<TransactionData>> =
        transactionRepository.getAll().map { transactionList ->
            transactionList.map { it.toTransactionData() }
        }
}