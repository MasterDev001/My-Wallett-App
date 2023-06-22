package com.example.r_usecase.usecases.transactionUseCase

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonCurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.TransactionData
import com.example.a_common.data.WalletData
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import com.example.z_entity.db.entity.toMyTransaction
import com.example.z_entity.db.models.toMyPersonCurrency
import com.example.z_entity.repository.PersonCurrencyRepository
import com.example.z_entity.repository.TransactionRepository
import java.util.UUID
import javax.inject.Inject

class BorrowUseCase @Inject constructor(
    private val personCurrencyRepository: PersonCurrencyRepository,
    private val transactionRepository: TransactionRepository,
    private val walletsUseCase: WalletsUseCase
) {

    suspend operator fun invoke(
        amount: String,
        personData: PersonData,
        walletData: WalletData,
        currencyData: CurrencyData,
        transaction: TransactionData
    ) {
        transactionRepository.add(transaction.toMyTransaction())

        if (personCurrencyRepository.isPersonCurrenciesCurrencyExists(
                personData.id,
                currencyData.id
            )
        ) {
            val personCurrency = personCurrencyRepository.getPersonCurrency(
                personData.id,
                currencyData.id
            )
            val newBalance =
                personCurrency.currencyBalance - amount.toDouble()
            if (newBalance == 0.0) {
                personCurrencyRepository.deletePersonCurrency(personCurrency.id)
            } else {
                personCurrencyRepository.updatePersonCurrency(
                    personCurrency.copy(
                        currencyBalance = newBalance
                    )
                )
            }
        } else {
            personCurrencyRepository.addPersonCurrency(
                PersonCurrencyData(
                    id = UUID.randomUUID().toString(),
                    personId = personData.id,
                    currencyId = currencyData.id,
                    currencyBalance = 0 - amount.toDouble(),
                    rate = currencyData.rate
                ).toMyPersonCurrency()
            )
        }
        walletsUseCase.inComeUseCase.invoke(
            amount.toDouble(),
            currencyData,
            walletData
        )
    }
}