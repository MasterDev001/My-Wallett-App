package com.example.r_usecase.usecases.transactionUseCase

data class TransactionUseCase(
    val addTransaction: AddTransactionUseC,
    val deleteTransaction: DeleteTransactionUseC,
    val getAllTransactions: GetAllTransactionsUseC,
    val borrowUseCase: BorrowUseCase,
    val lendUseCase: LendUseCase,
    val convertUseCase: ConvertUseCase
)